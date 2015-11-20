package com.cloudsherpas.droolsample.service.auth;


import java.util.Date;

import com.cloudsherpas.droolsample.dto.UserDTO;

public interface HttpRequestAuthorizationService {

    String generateToken(Date expiry, UserDTO user, String origin);

    String getUsernameFromToken(String token, String origin);

    boolean isTokenValid(String token, String origin, String username);

}
