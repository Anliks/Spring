package com.edu.ulab.app.storage;


import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.repository.AbstractRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class Storage implements AbstractRepository {

    private Long initialSequence;

    private Map<Long, Object> holder;

    public Storage() {
        initialSequence = 0L;
        holder = new HashMap<>();
    }

    @Override
    public Object getById(Long id) {
        if (!holder.containsKey(id)) {
            throw new NotFoundException("id not found");
        }
        return holder.get(id);
    }

    @Override
    public <T> T create(T t) {
        holder.put(initialSequence++, t);
        return t;
    }

    @Override
    public <T> T update(T t) {
        if(t instanceof User user) {
            holder.put(user.getId(), t);
        }
        else if(t instanceof Book book) {
            holder.put(book.getId(), book);
        }
        return t;
    }

    @Override
    public void delete(Long id) {
        if(!holder.containsKey(id)) {
            throw new NotFoundException("not found id from delete");
        }
    holder.remove(id);
    }

    @Override
    public List<Book> getBooks() {
        return holder.values().stream().filter(book -> book instanceof Book).map(this::getBook).toList();
    }

    @Override
    public Long getCurrentId() {
        return initialSequence;
    }
    private Book getBook(Object object) {
        return (Book) object;
    }


    //todo создать хранилище в котором будут содержаться данные
    // сделать абстракции через которые можно будет производить операции с хранилищем
    // продумать логику поиска и сохранения
    // продумать возможные ошибки
    // учесть, что при сохранеии юзера или книги, должен генерироваться идентификатор
    // продумать что у узера может быть много книг и нужно создать эту связь
    // так же учесть, что методы хранилища принимают друго тип данных - учесть это в абстракции
}
