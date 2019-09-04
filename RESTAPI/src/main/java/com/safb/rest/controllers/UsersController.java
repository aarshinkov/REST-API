package com.safb.rest.controllers;

import com.safb.rest.dto.*;
import com.safb.rest.entity.*;
import com.safb.rest.exceptions.*;
import com.safb.rest.response.*;
import com.safb.rest.services.*;
import java.util.*;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
public class UsersController
{

  @Autowired
  private UserService userService;

  @GetMapping(produces =
  {
    MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
  })
  public List<UserDto> getUsers(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "limit", defaultValue = "5") Integer limit)
  {
    List<User> users = userService.getUsers(page, limit);

    List<UserDto> userDtos = new ArrayList<>();

    ModelMapper modelMapper = new ModelMapper();

    for (User user : users)
    {
      UserDto userDto = modelMapper.map(user, UserDto.class);

      if (!userDtos.contains(userDto))
      {
        userDtos.add(userDto);
      }
    }

    return userDtos;
  }

  @GetMapping(value = "/{publicId}", produces =
  {
    MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
  })
  public UserDto getUser(@PathVariable("publicId") String publicId)
  {
    User user = userService.getUser(publicId);

    if (user == null)
    {
      throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
    }

    ModelMapper modelMapper = new ModelMapper();
    UserDto userDto = modelMapper.map(user, UserDto.class);

    return userDto;
  }
}
