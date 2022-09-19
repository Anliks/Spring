package com.edu.ulab.app.repository;

import java.util.List;

/**
 * Абстрактный класс для хранения сущностей Книг.
 * ID сущности генерируется, исходя из initialSequence
 *
 * @param <N>
 */

public abstract class AbstractBookRepository<N> {
    protected long initialSequence = 0;
    protected List<N> holder;

    protected abstract long setInitialSequence();

    protected abstract void setHolder();

}
