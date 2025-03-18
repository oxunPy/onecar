package com.example.onecar.service.I.base;

import com.example.onecar.dto.BaseDto;

import java.util.List;

public interface ICrudService<T extends BaseDto> {
    void add(T item);

    void update(T item);

    void delete(T item);

    List<T> findAll();
}
