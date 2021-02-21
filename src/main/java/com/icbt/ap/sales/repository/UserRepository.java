package com.icbt.ap.sales.repository;

import com.icbt.ap.sales.entity.User;
import com.icbt.ap.sales.repository.main.CrudRepository;

/**
 * @author Tharindu Eranga
 * @date Sun 21 Feb 2021
 */
public interface UserRepository extends CrudRepository<String, User> {
    User findByUserName(String username);
}
