package com.cloudsherpas.droolsample.service;

import com.cloudsherpas.droolsample.api.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResource user = this.userService.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return new User(user.getUsername(), user.getPassword(),
                            AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole()));
        }
    }

    public UserResource loadUserDTOByUsername(String username) throws UsernameNotFoundException {
        UserResource user = this.userService.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return user;
        }
    }

    public UserDetails loadUserByUserId(Long userId) throws UsernameNotFoundException {
        UserResource user = this.userService.getUserByUserID(userId);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with userid '%s'.", userId));
        } else {
            return new User(user.getUsername(), user.getPassword(),
                            AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole()));
        }
    }
}
