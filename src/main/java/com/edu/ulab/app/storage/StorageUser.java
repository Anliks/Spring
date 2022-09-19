package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.repository.AbstractUserRepository;
import com.edu.ulab.app.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StorageUser extends AbstractUserRepository<User> implements UserRepository {

    @Override
    protected long setInitialSequence() {
        return initialSequence += 1;
    }

    @PostConstruct
    protected void setHolder() {
        this.holder = new ArrayList<>();
    }

    @Override
    public User getById(long id) {
        if (!(holder.isEmpty())) {
            for (User el : holder) {
                if (el.getId() == id) {return el;}
            }
        }
        throw new NotFoundException("Пользователь не найден или в storage пусто!");
    }

    @Override
    public User createUser(User user) {
        user.setId(setInitialSequence());
        holder.add(user);
        return user;
    }

    @Override
    public void deleteUser(long id) {
        if (!(holder.isEmpty())) {
            holder.removeIf(el -> el.getId() == id);
        } else {
            throw new NotFoundException("Пользователь не найден или в storage пусто!");
        }
    }

    @Override
    public User updateUser(User user) {
        if (holder.isEmpty()) {
            throw new NotFoundException("пользователи отсутствуют! изменять нечего");
        }
        holder.removeIf(el -> el.getId() == user.getId());
        holder.add(user);
        return user;
    }

    @Override
    public void addListBooksToUser(List<Long> list, Long id) {
        for (User el : holder) {
            if (el.getId() == id) {
                el.setUserBooks(list);
                System.out.println(holder);
            }
        }
    }

}
