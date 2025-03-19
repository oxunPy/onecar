package com.example.onecar.service;

import com.example.onecar.config.security.JwtUtil;
import com.example.onecar.dto.UserDto;
import com.example.onecar.dto.records.AuthResponse;
import com.example.onecar.dto.records.UsernamePassword;
import com.example.onecar.dto.response.OneCarHttpResponse;
import com.example.onecar.entity.UserEntity;
import com.example.onecar.repository.UserRepository;
import com.example.onecar.service.I.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public OneCarHttpResponse<UserDto> add(UserDto item) {
        Optional<UserEntity> userOpt = userRepository.findUserByPhoneOrEmail(item.getPhone(), item.getEmail());
        if(userOpt.isPresent()) {
            return OneCarHttpResponse.<UserDto>builder()
                    .message("Phone or email is already taken!")
                    .status(OneCarHttpResponse.Status.FAILED)
                    .build();
        }

        UserEntity newUser = new UserEntity();
        BeanUtils.copyProperties(item, newUser);
        newUser.forCreate();
        newUser.setPassword(passwordEncoder.encode(item.getPassword()));
        // save new user
        userRepository.save(newUser);

        item.setId(newUser.getId());
        item.setCreatedDate(newUser.getCreatedDate());
        return OneCarHttpResponse.<UserDto>builder()
                .message("User is registered successfully")
                .status(OneCarHttpResponse.Status.SUCCESS)
                .object(item)
                .build();
    }

    @Override
    public OneCarHttpResponse<UserDto> update(UserDto item) {
        Optional<UserEntity> userOpt = userRepository.findUserByPhoneOrEmail(item.getPhone(), item.getEmail());
        if(userOpt.isPresent()) {
            // Phone or email cannot be changed!

            if(item.getFirstName() != null) {
                userOpt.get().setFirstName(item.getFirstName());
            }

            if(item.getLastName() != null) {
                userOpt.get().setLastName(item.getLastName());
            }

            if(item.getMiddleName() != null) {
                userOpt.get().setMiddleName(item.getMiddleName());
            }

            if(item.getPassword() != null) {
                userOpt.get().setPassword(passwordEncoder.encode(item.getPassword()));
            }

            userRepository.save(userOpt.get());
            return OneCarHttpResponse.<UserDto>builder()
                    .status(OneCarHttpResponse.Status.SUCCESS)
                    .message("User data has been changed successfully!")
                    .object(userOpt.get().toDto())
                    .build();
        }

        return OneCarHttpResponse.<UserDto>builder()
                .message("Username not found!")
                .status(OneCarHttpResponse.Status.FAILED)
                .build();
    }

    @Override
    public OneCarHttpResponse<Boolean> delete(Long id) {
        Optional<UserEntity> userOpt = userRepository.findById(id);
        if(userOpt.isPresent()) {
            userOpt.get().forDelete();
            userRepository.save(userOpt.get());

            return OneCarHttpResponse.<Boolean>builder()
                    .status(OneCarHttpResponse.Status.SUCCESS)
                    .message("User is deleted successfully!")
                    .object(Boolean.TRUE)
                    .build();
        }

        return OneCarHttpResponse.<Boolean>builder()
                .status(OneCarHttpResponse.Status.FAILED)
                .message("Active user not found")
                .object(Boolean.FALSE)
                .build();
    }

    @Override
    public OneCarHttpResponse<List<UserDto>> findAll() {

        List<UserEntity> allUsers = userRepository.findAll();
        if(allUsers.isEmpty()) {
           return OneCarHttpResponse.<List<UserDto>>builder()
                   .status(OneCarHttpResponse.Status.SUCCESS)
                   .message("No users found!")
                   .build();
        }

        return OneCarHttpResponse.<List<UserDto>>builder()
                .status(OneCarHttpResponse.Status.SUCCESS)
                .message("Here they are all users list!")
                .object(allUsers.stream().map(UserEntity::toDto).toList())
                .build();
    }

    @Override
    public OneCarHttpResponse<AuthResponse> login(UsernamePassword up) {
        Optional<UserEntity> user = userRepository.findUserByPhone(up.username());
        if(user.isPresent()) {
            if(passwordEncoder.matches(up.password(), user.get().getPassword())) {
                String newToken = jwtUtil.generateToken(user.get().getPhone());
                return OneCarHttpResponse.<AuthResponse>builder()
                        .message("Login is successfull!")
                        .status(OneCarHttpResponse.Status.SUCCESS)
                        .object(new AuthResponse(newToken, true, new Date(System.currentTimeMillis() + JwtUtil.EXPIRATION_DATE)))
                        .build();
            }

            else {
                return OneCarHttpResponse.<AuthResponse>builder()
                        .message("Password is incorrect!")
                        .status(OneCarHttpResponse.Status.FAILED)
                        .object(new AuthResponse(null, false, new Date()))
                        .build();
            }
        }

        return OneCarHttpResponse.<AuthResponse>builder()
                .message("Username not found!")
                .status(OneCarHttpResponse.Status.FAILED)
                .object(new AuthResponse(null, false, new Date()))
                .build();
    }
}
