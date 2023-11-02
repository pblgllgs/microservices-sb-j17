package com.pblgllgs.users.controllers;

import com.pblgllgs.users.dto.UserDto;
import com.pblgllgs.users.model.requests.CreateUserRequestModel;
import com.pblgllgs.users.model.responses.CreateUserResponseModel;
import com.pblgllgs.users.model.responses.UserResponseModel;
import com.pblgllgs.users.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final ServletWebServerApplicationContext applicationContext;
    private final UserService userService;
    private final ModelMapper mapper;
    private final Environment environment;

    @GetMapping("/status/check")
    public String status() {
        return "Users Status UP on " + applicationContext.getWebServer().getPort()+" and "+ environment.getProperty("token.secret");
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseModel> saveUser(@Valid @RequestBody CreateUserRequestModel userRequestModel) {
        UserDto userDto = mapper.map(userRequestModel,UserDto.class);
        UserDto userDtoSaved = userService.createUser(userDto);
        CreateUserResponseModel userResponseModel =  mapper.map(userDtoSaved,CreateUserResponseModel.class);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseModel);
    }

    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId){
        UserDto userDto = userService.findUserById(userId);
        UserResponseModel returnValue =  new ModelMapper().map(userDto,UserResponseModel.class);
        return new ResponseEntity<>(returnValue,HttpStatus.OK);
    }
}
