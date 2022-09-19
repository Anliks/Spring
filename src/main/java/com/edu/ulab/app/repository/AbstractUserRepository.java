package com.edu.ulab.app.repository;

import java.util.List;

/**
 * Абстрактный класс для хранения сущностей Пользователя.
 * ID сущности генерируется, исходя из initialSequence
 *
 * @param <T>
 */

public abstract class AbstractUserRepository<T> {
    protected long initialSequence = 0;
    protected List<T> holder;

    protected abstract long setInitialSequence();

    protected abstract void setHolder();
}
