package com.example.onecar.service.I;

public interface ICrudService<T> {
    void add(T item);

    void update(T item);

    void delete(T item);
}
