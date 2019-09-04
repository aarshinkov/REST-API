package com.safb.rest.services;

import com.safb.rest.entity.*;
import java.util.*;

public interface UserService
{
  List<User> getUsers(Integer page, Integer limit);

  User getUser(String publicId);
}
