package com.safb.rest.services;

import com.safb.rest.dto.*;
import com.safb.rest.entity.*;
import com.safb.rest.exceptions.*;
import com.safb.rest.model.*;
import com.safb.rest.repository.*;
import com.safb.rest.response.*;
import com.safb.rest.utils.*;
import java.util.*;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

@Service
public class UserServiceImpl implements UserService
{
  private static final Integer PUBLIC_ID_LENGTH = 30;

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public List<User> getUsers(Integer page, Integer limit)
  {
    if (page > 0)
    {
      page--;
    }

    Pageable pageableRequest = PageRequest.of(page, limit);

    Page<User> usersPage = usersRepository.findAll(pageableRequest);

    List<User> usersList = usersPage.getContent();

    return usersList;
  }

  @Override
  public User getUser(String publicId)
  {
    User user = usersRepository.findByPublicId(publicId);

    if (user == null)
    {
      throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
    }

    return user;
  }

  @Override
  public UserCreateDto createUser(UserModel userModel)
  {
    if (userModel == null)
    {
      throw new UserServiceException(ErrorMessages.OBJECT_EMPTY.getErrorMessage());
    }

    String genPublicId = Utils.generateUserId(PUBLIC_ID_LENGTH);

    //TODO encode password
    String encodedPassword = passwordEncoder.encode(userModel.getPassword());
    userModel.setPassword(encodedPassword);

    ModelMapper modelMapper = new ModelMapper();

    User user = modelMapper.map(userModel, User.class);
    user.setPublicId(genPublicId);
    usersRepository.save(user);

    UserCreateDto userCreateDto = modelMapper.map(user, UserCreateDto.class);

    return userCreateDto;
  }
}
