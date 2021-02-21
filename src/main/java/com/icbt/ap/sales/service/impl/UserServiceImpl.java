package com.icbt.ap.sales.service.impl;

import com.icbt.ap.sales.controller.v1.model.request.UserLoginRequest;
import com.icbt.ap.sales.entity.Branch;
import com.icbt.ap.sales.entity.User;
import com.icbt.ap.sales.exception.CustomServiceException;
import com.icbt.ap.sales.repository.UserRepository;
import com.icbt.ap.sales.service.BranchService;
import com.icbt.ap.sales.service.UserService;
import com.icbt.ap.sales.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tharindu Eranga
 * @date Wed 17 Feb 2021
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BranchService branchService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void add(User user) {
        /*checks whether the user name already exists*/
        final User userByUserName = userRepository.findByUserName(user.getUsername());
        if (userByUserName != null) throwUserNameAlreadyExistException();
        /*checks whether the given branch id exists and sets explicitly*/
        final Branch branch = branchService.getById(user.getBranchId());
        user.setBranchId(branch.getId());
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        /*validates the incoming data*/
        final User userById = getById(user.getId());
        /*checks whether the user name already exists*/
        final User userByUserName = userRepository.findByUserName(user.getUsername());
        if ((userByUserName != null) && (!userByUserName.getId().equals(user.getId())))
            throwUserNameAlreadyExistException();
        if (StringUtil.isNotBlank(user.getBranchId())) {
            final Branch branch = branchService.getById(user.getBranchId());
            user.setBranchId(branch.getId());
        }

        userById.setUsername(user.getUsername());
        userById.setPassword(user.getPassword());
        userById.setUserRole(user.getUserRole());

        userRepository.update(userById);
    }

    @Override
    public void delete(String id) {
        final User user = getById(id);
        userRepository.delete(user.getId());
    }

    @Override
    public User getById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new CustomServiceException(
                "error.validation.common.not.found.code",
                "error.validation.userid.not.found.message",
                new String[]{id}
        ));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User authenticate(UserLoginRequest loginRequest) {
        final User user = userRepository.findByUserName(loginRequest.getUsername());
        if (user == null) throw new CustomServiceException(
                "error.validation.common.not.found.code",
                "error.validation.username.not.found.message",
                new String[]{loginRequest.getUsername()}
        );
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
            throw new CustomServiceException(
                    "error.validation.common.already.exist.code",
                    "error.validation.invalid.password.message"
            );
        user.setPassword(null);
        return user;
    }


    /*Internal functions below*/

    private void throwUserNameAlreadyExistException() {
        throw new CustomServiceException(
                "error.validation.common.already.exist.code",
                "error.validation.user.name.already.exist.message"
        );
    }

}
