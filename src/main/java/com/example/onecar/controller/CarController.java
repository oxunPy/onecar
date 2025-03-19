package com.example.onecar.controller;

import com.example.onecar.dto.CarDto;
import com.example.onecar.dto.RepairDto;
import com.example.onecar.dto.UserDto;
import com.example.onecar.dto.response.OneCarHttpResponse;
import com.example.onecar.service.I.ICarService;
import com.example.onecar.utils.ValidatorUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {
    private final ICarService carService;

    public CarController(ICarService carService) {
        this.carService = carService;
    }

    @PostMapping("/register-my-car")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public OneCarHttpResponse<CarDto> registerMyCar(@Valid @RequestBody CarDto carDto) {
        return carService.add(carDto);
    }

    @PutMapping("/update-my-car")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public OneCarHttpResponse<CarDto> updateMyCar(@RequestBody CarDto carDto) {
        if(ValidatorUtils.notValidId(carDto.getId())) {
            return OneCarHttpResponse.<CarDto>builder()
                    .status(OneCarHttpResponse.Status.BAD_REQUEST)
                    .message("Car id is not valid!")
                    .build();
        }

        return carService.update(carDto);
    }

    @DeleteMapping("/delete-my-car")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public OneCarHttpResponse<Boolean> deleteMyCar(@RequestParam("car_id") Long carId) {
        if(ValidatorUtils.notValidId(carId)) {
            return OneCarHttpResponse.<Boolean>builder()
                    .status(OneCarHttpResponse.Status.BAD_REQUEST)
                    .message("Car id is not valid!")
                    .object(Boolean.FALSE)
                    .build();
        }

        return carService.delete(carId);
    }
}
