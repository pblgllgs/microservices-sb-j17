package com.pblgllgs.users.services.impl;

import com.pblgllgs.users.clients.AlbumClient;
import com.pblgllgs.users.dto.UserDto;
import com.pblgllgs.users.model.entities.UserEntity;
import com.pblgllgs.users.model.responses.AlbumResponseModel;
import com.pblgllgs.users.repositories.UserRepository;
import com.pblgllgs.users.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AlbumClient albumClient;

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userEntity);
        return mapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException("USER_NOT_FOUND");
        }
        return mapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto findUserById(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new UsernameNotFoundException("user not found");
        }
        UserDto userDto = mapper.map(userEntity, UserDto.class);
        List<AlbumResponseModel> userAlbums = albumClient.getAlbums(userId);
        userDto.setAlbums(userAlbums);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("USER_NOT_FOUND");
        }
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
    }
}
