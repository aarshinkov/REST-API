package com.safb.rest.services;

import com.safb.rest.dto.*;
import com.safb.rest.entity.*;
import com.safb.rest.model.*;
import java.util.*;

public interface UserService
{
  List<User> getUsers(Integer page, Integer limit);

  User getUser(String publicId);

  UserCreateDto createUser(UserModel userModel);
}
