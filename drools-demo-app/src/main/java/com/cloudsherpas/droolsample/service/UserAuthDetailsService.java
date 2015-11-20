package com.cloudsherpas.prototype.middleware.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cloudsherpas.droolsample.dto.UserDTO;

public class UserAuthDetailsService implements UserDetailsService {

      @Autowired
      private UserService userService;

      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = this.userService.getUserByUsername(username);

        if (user == null) {
          throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
          return UserDetailsFactory.create(user);
        }
      }

      public UserDTO loadUserDTOByUsername(String username) throws UsernameNotFoundException {
          UserDTO user = this.userService.getUserByUsername(username);

          if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
          } else {
            return user;
          }
        }

      public UserDetails loadUserByUserId(Long userId) throws UsernameNotFoundException {
        UserDTO user = this.userService.getUserByUserID(userId);

        if (user == null) {
          throw new UsernameNotFoundException(String.format("No user found with userid '%s'.", userId));
        } else {
          return UserDetailsFactory.create(user);
        }
      }
}
