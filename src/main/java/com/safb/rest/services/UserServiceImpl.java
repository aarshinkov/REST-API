package com.safb.rest.services;

import com.safb.rest.dto.UserCreateDto;
import com.safb.rest.entity.User;
import com.safb.rest.exceptions.UserServiceException;
import com.safb.rest.model.UserModel;
import com.safb.rest.repository.UsersRepository;
import com.safb.rest.response.ErrorMessages;
import com.safb.rest.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Integer PUBLIC_ID_LENGTH = 30;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers(Integer page, Integer limit) {
        if (page > 0) {
            page--;
        }

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<User> usersPage = usersRepository.findAll(pageableRequest);

        List<User> usersList = usersPage.getContent();

        return usersList;
    }

    @Override
    public User getUser(String publicId) {
        User user = usersRepository.findByPublicId(publicId);

        if (user == null) {
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        return user;
    }

    @Override
    public UserCreateDto createUser(UserModel userModel) {
        if (userModel == null) {
            throw new UserServiceException(ErrorMessages.OBJECT_EMPTY.getErrorMessage());
        }

        String genPublicId = Utils.generateUserId(PUBLIC_ID_LENGTH);

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
