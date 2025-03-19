package com.example.onecar.service.I;

import com.example.onecar.dto.UserDto;
import com.example.onecar.dto.records.AuthResponse;
import com.example.onecar.dto.records.UsernamePassword;
import com.example.onecar.dto.response.OneCarHttpResponse;
import com.example.onecar.service.I.base.ICrudService;

public interface IUserService extends ICrudService<UserDto> {
    OneCarHttpResponse<AuthResponse> login(UsernamePassword up);
}
