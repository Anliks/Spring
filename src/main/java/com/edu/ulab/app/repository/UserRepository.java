package com.edu.ulab.app.repository;

import com.edu.ulab.app.entity.User;

import java.util.List;

public interface UserRepository {


    User getById(long id);

    User createUser(User user);

    void deleteUser(long id);

    User updateUser(User user);

    void addListBooksToUser(List<Long> list, Long id);

}
