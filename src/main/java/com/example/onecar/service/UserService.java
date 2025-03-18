package com.example.onecar.service;

import com.example.onecar.dto.UserDto;
import com.example.onecar.repository.UserRepository;
import com.example.onecar.service.I.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(UserDto item) {

    }

    @Override
    public void update(UserDto item) {

    }

    @Override
    public void delete(UserDto item) {

    }

    @Override
    public List<UserDto> findAll() {
        return List.of();
    }
}
