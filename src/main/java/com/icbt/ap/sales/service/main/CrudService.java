package com.icbt.ap.sales.service.main;

import java.util.List;

/**
 * @author Tharindu Eranga
 * @date Mon 08 Feb 2021
 */
public interface CrudService<K, T> {
    void add(T t);

    void update(T t);

    void delete(K id);

    T getById(K id);

    List<T> getAll();
}
