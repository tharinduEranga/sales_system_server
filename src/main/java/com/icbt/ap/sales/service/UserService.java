package com.icbt.ap.sales.service;

import com.icbt.ap.sales.controller.v1.model.request.UserLoginRequest;
import com.icbt.ap.sales.entity.User;
import com.icbt.ap.sales.service.main.CrudService;
import org.springframework.stereotype.Service;

/**
 * @author Tharindu Eranga
 * @date Sun 21 Feb 2021
 */
@Service
public interface UserService extends CrudService<String, User> {

    User authenticate(UserLoginRequest loginRequest);
}
