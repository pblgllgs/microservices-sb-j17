package com.pblgllgs.users.controllers;

import com.pblgllgs.users.dto.UserDto;
import com.pblgllgs.users.model.requests.CreateUserRequestModel;
import com.pblgllgs.users.model.responses.CreateUserResponseModel;
import com.pblgllgs.users.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final ServletWebServerApplicationContext applicationContext;
    private final UserService userService;
    private final ModelMapper mapper;

    @GetMapping("/status/check")
    public String status() {
        return "Users Status UP on " + applicationContext.getWebServer().getPort();
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseModel> saveUser(@Valid @RequestBody CreateUserRequestModel userRequestModel) {
        UserDto userDto = mapper.map(userRequestModel,UserDto.class);
        UserDto userDtoSaved = userService.createUser(userDto);
        CreateUserResponseModel userResponseModel =  mapper.map(userDtoSaved,CreateUserResponseModel.class);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseModel);
    }
}
