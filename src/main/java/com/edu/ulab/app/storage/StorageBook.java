package com.edu.ulab.app.storage;


import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.repository.AbstractBookRepository;
import com.edu.ulab.app.repository.BookRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Repository
public class StorageBook extends AbstractBookRepository<Book> implements BookRepository {

    @Override
    protected long setInitialSequence() {
     return initialSequence += 1;
    }

    @PostConstruct
    protected void setHolder() {
        this.holder = new ArrayList<>();
    }

    @Override
    public Book getById(long id) {
        if(!(holder.isEmpty())) {
            for (Book el : holder) {
                if(el.getId() == id) {
                    return el;
                }
            }
        }
        return null;
    }

    @Override
    public Book createBook(Book book) {
        book.setId(setInitialSequence());
        holder.add(book);
        return book;
    }

    @Override
    public void deleteBook(long id) {
        holder.removeIf(el -> el.getId() == id);
        }


    @Override
    public Book updateBook(Book book) {
        holder.removeIf(el -> el.getId() == book.getId());
        holder.add(book);
        return book;
    }

}
