package com.safb.rest.dao;

import com.safb.rest.dto.UserUpdateDto;

public interface UserDao
{
  boolean updateUser(UserUpdateDto userUpdateDto);
}
