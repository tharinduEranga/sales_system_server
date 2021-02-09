package com.icbt.ap.sales.repository.main;

import java.util.List;
import java.util.Optional;

/**
 * @author Tharindu Eranga
 * @date Mon 08 Feb 2021
 */
public interface CrudRepository<K, T> {
    List<T> findAll();

    Optional<T> findById(K id);

    void save(T entity);

    void update(T entity);

    void delete(K id);

}
