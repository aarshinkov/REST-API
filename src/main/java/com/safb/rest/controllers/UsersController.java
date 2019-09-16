package com.safb.rest.controllers;

import com.safb.rest.dto.UserCreateDto;
import com.safb.rest.dto.UserDto;
import com.safb.rest.dto.UserUpdateDto;
import com.safb.rest.entity.User;
import com.safb.rest.exceptions.UserServiceException;
import com.safb.rest.model.UserCreateModel;
import com.safb.rest.response.ErrorMessages;
import com.safb.rest.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getUsers(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        List<User> users = userService.getUsers(page, limit);

        List<UserDto> userDtos = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();

        for (User user : users) {
            UserDto userDto = modelMapper.map(user, UserDto.class);

            if (!userDtos.contains(userDto)) {
                userDtos.add(userDto);
            }
        }

        return userDtos;
    }

    @GetMapping(value = "/{publicId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto getUser(@PathVariable("publicId") String publicId) {
        User user = userService.getUser(publicId);

        if (user == null) {
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(user, UserDto.class);

        return userDto;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserCreateDto createUser(@Valid @RequestBody UserCreateModel userCreateModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new UserServiceException(ErrorMessages.FIELD_NOT_MATCHING_CRITERIA.getErrorMessage());
        }

        if (userService.isUserExistWithEmail(userCreateModel.getEmail())) {
            throw new UserServiceException(ErrorMessages.USER_EXISTS.getErrorMessage());
        }

        return userService.createUser(userCreateModel);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserUpdateDto updateUser(@RequestBody UserUpdateDto userUpdateDto) {

        if (!userService.isUserExistWithPublicId(userUpdateDto.getPublicId())) {
            throw new UserServiceException(ErrorMessages.USER_NO_EXIST.getErrorMessage());
        }

        return userService.updateUser(userUpdateDto);
    }
}
