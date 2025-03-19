package com.example.onecar.controller;

import com.example.onecar.dto.UserDto;
import com.example.onecar.dto.response.OneCarHttpResponse;
import com.example.onecar.service.I.IUserService;
import com.example.onecar.utils.ValidatorUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PutMapping("/update")
    public OneCarHttpResponse<UserDto> updateUser(@RequestBody UserDto userDto) {
        if(ValidatorUtils.notValidId(userDto.getId())) {
            return OneCarHttpResponse.<UserDto>builder()
                    .message("User id is not valid!")
                    .status(OneCarHttpResponse.Status.BAD_REQUEST)
                    .build();
        }

        return userService.update(userDto);
    }

    @DeleteMapping("/delete")
    public OneCarHttpResponse<Boolean> deleteUser(@RequestParam("user_id") Long userId) {
        if(ValidatorUtils.notValidId(userId)) {
            return OneCarHttpResponse.<Boolean>builder()
                    .message("User id is not valid!")
                    .status(OneCarHttpResponse.Status.BAD_REQUEST)
                    .object(Boolean.FALSE)
                    .build();
        }

        return userService.delete(userId);
    }
}
