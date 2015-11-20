package com.cloudsherpas.droolsample.dto.factory;

import org.springframework.security.core.authority.AuthorityUtils;

import com.cloudsherpas.droolsample.dto.UserDTO;
import com.cloudsherpas.droolsample.dto.UserDetailsDTO;

public class UserDetailsFactory {

  public static UserDetailsDTO create(UserDTO user) {
    return new UserDetailsDTO(
      user.getId(),
      user.getUsername(),
      user.getPassword(),
      user.getRole(),
      AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole())
    );
  }

}