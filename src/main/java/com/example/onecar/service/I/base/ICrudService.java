package com.example.onecar.service.I.base;

import com.example.onecar.dto.BaseDto;
import com.example.onecar.dto.response.OneCarHttpResponse;

import java.util.List;

public interface ICrudService<T extends BaseDto> {
    OneCarHttpResponse<T> add(T item);

    OneCarHttpResponse<T> update(T item);

    OneCarHttpResponse<Boolean> delete(Long id);

    OneCarHttpResponse<List<T>> findAll();
}
