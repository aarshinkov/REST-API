package com.safb.rest.services;

import com.safb.rest.dao.UserDao;
import com.safb.rest.dto.UserCreateDto;
import com.safb.rest.dto.UserUpdateDto;
import com.safb.rest.entity.User;
import com.safb.rest.exceptions.UserServiceException;
import com.safb.rest.model.UserCreateModel;
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
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers(Integer page, Integer limit) {
        if (page > 0) {
            page--;
        }

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<User> usersPage = usersRepository.findAll(pageableRequest);

        return usersPage.getContent();
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
    public boolean isUserExistWithEmail(String email) {

        User checkUser = usersRepository.findByEmail(email);

        return checkUser != null;
    }

    @Override
    public boolean isUserExistWithPublicId(String publicId) {

        User checkUser = usersRepository.findByPublicId(publicId);

        return checkUser != null;
    }

    @Override
    public UserCreateDto createUser(UserCreateModel userCreateModel) {
        if (userCreateModel == null) {
            throw new UserServiceException(ErrorMessages.OBJECT_EMPTY.getErrorMessage());
        }

        String genPublicId = Utils.generateUserId(PUBLIC_ID_LENGTH);

        String encodedPassword = passwordEncoder.encode(userCreateModel.getPassword());
        userCreateModel.setPassword(encodedPassword);

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userCreateModel, User.class);
        user.setPublicId(genPublicId);

        usersRepository.save(user);

        return modelMapper.map(user, UserCreateDto.class);
    }

    @Override
    public UserUpdateDto updateUser(UserUpdateDto userUpdateDto) {
        if (!userDao.updateUser(userUpdateDto)) {
            throw new UserServiceException(ErrorMessages.INTERNAL_SERVER_ERROR.getErrorMessage());
        }
        return userUpdateDto;
    }

    @Override
    public void deleteUser(String publicId) throws UserServiceException {
        User user = usersRepository.findByPublicId(publicId);

        if (user == null) {
            throw new UserServiceException(ErrorMessages.OBJECT_EMPTY.getErrorMessage());
        }

        usersRepository.delete(user);
    }
}
