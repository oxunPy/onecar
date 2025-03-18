package com.example.onecar.service;

import com.example.onecar.dto.CarDto;
import com.example.onecar.repository.CarRepository;
import com.example.onecar.service.I.ICarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements ICarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void add(CarDto item) {

    }

    @Override
    public void update(CarDto item) {

    }

    @Override
    public void delete(CarDto item) {

    }

    @Override
    public List<CarDto> findAll() {
        return List.of();
    }
}
