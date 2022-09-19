package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.repository.BookRepository;
import com.edu.ulab.app.repository.UserRepository;
import com.edu.ulab.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    BookRepository bookRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;

    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setAge(userDto.getAge());
        user.setFullName(userDto.getFullName());
        user.setTitle(userDto.getTitle());
        userDto.setId(userRepository.createUser(user).getId());
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.getById(userDto.getId());
        user.setTitle(userDto.getTitle());
        user.setAge(userDto.getAge());
        user.setFullName(userDto.getFullName());
        userDto.setId(userRepository.updateUser(user).getId());
        return userDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        UserDto userDto = new UserDto();
        User user = userRepository.getById(id);
        userDto.setId(user.getId());
        userDto.setAge(user.getAge());
        userDto.setTitle(user.getTitle());
        userDto.setFullName(user.getFullName());
        return userDto;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteUser(id);
    }

    //костыль в виде создания листа id books
    @Override
    public void createListBooksToUser(List<Long> list, Long id) {
        userRepository.addListBooksToUser(list, id);
    }

    @Override
    public List<Long> listBooksFromUser(Long id) {
        return userRepository.getById(id).getUserBooks().stream().filter(Objects::nonNull).toList();
    }

}
