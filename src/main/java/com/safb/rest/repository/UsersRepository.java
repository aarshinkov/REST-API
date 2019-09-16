package com.safb.rest.repository;

import com.safb.rest.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends PagingAndSortingRepository<User, Integer> {

    User findByUserId(Integer userId);

    User findByPublicId(String publicId);

    User findByEmail(String username);
}
