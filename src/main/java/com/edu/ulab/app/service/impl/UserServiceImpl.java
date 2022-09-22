package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.exception.NotValidDataException;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.repository.AbstractRepository;
import com.edu.ulab.app.service.UserService;
import com.edu.ulab.app.validation.IsValidUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserServiceImpl implements UserService {


    private AbstractRepository abstractRepository;
    UserMapper userMapper;

    @Autowired
    public UserServiceImpl(AbstractRepository abstractRepository, UserMapper userMapper) {
        this.abstractRepository = abstractRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user;
        if (IsValidUser.isValid(userDto)) {
            userDto.setId(abstractRepository.getCurrentId());
            user = abstractRepository.create(userMapper.userDtoToUser(userDto));
        } else {
            throw new NotValidDataException("Not valid object");
        }
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        if (!IsValidUser.isValid(userDto)) {
            throw new NotValidDataException("Not valid update " + userDto);
        }
        UserDto inHolderUser = getUserById(userDto.getId());
        inHolderUser.setFullName(userDto.getFullName());
        inHolderUser.setTitle(userDto.getTitle());
        inHolderUser.setAge(userDto.getAge());
        abstractRepository.update(inHolderUser);
        return inHolderUser;
    }

    @Override
    public UserDto getUserById(Long id) {
        Object object = abstractRepository.getById(id);
        if (object instanceof User) {
            log.info("get user by id {}", object);
            return userMapper.userToUserDto((User) object);
        } else {
            throw new NotFoundException("id is not found");
        }
    }

    @Override
    public void deleteUserById(Long id) {
        Object object = abstractRepository.getById(id);
        if (object instanceof User) {
            abstractRepository.delete(id);
        } else {
            throw new NotValidDataException("not valid ids");
        }
    }
}
