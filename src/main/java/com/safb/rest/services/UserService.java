package com.safb.rest.services;

import com.safb.rest.dto.UserCreateDto;
import com.safb.rest.dto.UserUpdateDto;
import com.safb.rest.entity.User;
import com.safb.rest.exceptions.UserServiceException;
import com.safb.rest.model.UserCreateModel;

import java.util.List;

public interface UserService {

    List<User> getUsers(Integer page, Integer limit);

    User getUser(String publicId);

    boolean isUserExistWithEmail(String email);

    boolean isUserExistWithPublicId(String publicId);

    UserCreateDto createUser(UserCreateModel userCreateModel);

    UserUpdateDto updateUser(UserUpdateDto userUpdateDto);

    void deleteUser(String publicId) throws UserServiceException;
}
