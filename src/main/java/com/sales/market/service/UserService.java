package com.sales.market.service;

import com.sales.market.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService extends GenericService<User> {

    Page<User> findUsers(Pageable pageable);

    User findByEmail(String email);

    User findSystemUser();

    User findOnlyEnabledByEmailWithRoles(String email);

    boolean isUserRegistered(String email);

    int updatePasswordByEmail(String email, String newPassword);

    UserDetails findUserDetails(String email);

    User save(String firstName, String lastName, String email, String password);

    User update(User model);
}
