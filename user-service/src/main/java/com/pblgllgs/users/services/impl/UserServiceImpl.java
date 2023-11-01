package com.pblgllgs.users.services.impl;

import com.pblgllgs.users.dto.UserDto;
import com.pblgllgs.users.model.entities.UserEntity;
import com.pblgllgs.users.repository.UserRepository;
import com.pblgllgs.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userEntity);
        return mapper.map(userEntity,UserDto.class);
    }
}
