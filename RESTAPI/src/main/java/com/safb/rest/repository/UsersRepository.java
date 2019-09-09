package com.safb.rest.repository;

import com.safb.rest.entity.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.*;

@Component
public interface UsersRepository extends PagingAndSortingRepository<User, Integer>
{
  User findByUserId(Integer userId);

  User findByPublicId(String publicId);

  User findByEmail(String username);
}
