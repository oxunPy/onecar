package com.example.onecar.service;

import com.example.onecar.config.security.SecurityUtils;
import com.example.onecar.dto.CarDto;
import com.example.onecar.dto.response.OneCarHttpResponse;
import com.example.onecar.entity.CarEntity;
import com.example.onecar.entity.UserEntity;
import com.example.onecar.entity.base.BaseStatus;
import com.example.onecar.repository.CarRepository;
import com.example.onecar.service.I.ICarService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements ICarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public OneCarHttpResponse<CarDto> add(CarDto item) {
        // Fetch owner of the car by SecurityContext
        UserEntity owner = SecurityUtils.getAuthenticatedUser();
        if(owner == null) return null;

        // check uniqueness
        if(carRepository.existsByVehicleIdentityNum(item.getVinNumber(), item.getMark(), item.getModel())) {
            return OneCarHttpResponse.<CarDto>builder()
                    .message("Vehicle identity should be unique")
                    .status(OneCarHttpResponse.Status.BAD_REQUEST)
                    .build();
        }

        CarEntity newCar = new CarEntity();
        BeanUtils.copyProperties(item, newCar);
        newCar.forCreate();
        newCar.setOwnerId(owner.getId());
        carRepository.save(newCar);

        return OneCarHttpResponse.<CarDto>builder()
                .message("Car is added successfully!")
                .status(OneCarHttpResponse.Status.SUCCESS)
                .object(newCar.toDto())
                .build();
    }

    @Override
    public OneCarHttpResponse<CarDto> update(CarDto item) {
        // Fetch owner of the car by SecurityContext
        UserEntity owner = SecurityUtils.getAuthenticatedUser();
        if(owner == null) return null;

        Optional<CarEntity> carOpt = carRepository.findById(item.getId());
        if(carOpt.isPresent()) {
            if(item.getReleaseYear() != null) {
                carOpt.get().setReleaseYear(item.getReleaseYear());
            }

            carRepository.save(carOpt.get());
            return OneCarHttpResponse.<CarDto>builder()
                    .status(OneCarHttpResponse.Status.SUCCESS)
                    .message("Car is updated successfully!")
                    .object(carOpt.get().toDto())
                    .build();
        }

        return OneCarHttpResponse.<CarDto>builder()
                .message("Car is not found")
                .status(OneCarHttpResponse.Status.FAILED)
                .build();
    }

    @Override
    public OneCarHttpResponse<Boolean> delete(Long id) {
        Optional<CarEntity> carOpt = carRepository.findById(id);
        if(carOpt.isPresent()) {
            carOpt.get().forDelete();
            carRepository.save(carOpt.get());

            return OneCarHttpResponse.<Boolean>builder()
                    .message("Car is deleted successfully!")
                    .status(OneCarHttpResponse.Status.SUCCESS)
                    .object(Boolean.TRUE)
                    .build();
        }

        return OneCarHttpResponse.<Boolean>builder()
                .message("Car is not found")
                .status(OneCarHttpResponse.Status.FAILED)
                .object(Boolean.FALSE)
                .build();
    }

    @Override
    public OneCarHttpResponse<List<CarDto>> findAll() {
        List<CarEntity> cars = carRepository.findAllActive(BaseStatus.ACTIVE.ordinal());
        return OneCarHttpResponse.<List<CarDto>>builder()
                .message("All cars list!")
                .status(OneCarHttpResponse.Status.SUCCESS)
                .object(cars.stream().map(CarEntity::toDto).toList())
                .build();
    }
}
