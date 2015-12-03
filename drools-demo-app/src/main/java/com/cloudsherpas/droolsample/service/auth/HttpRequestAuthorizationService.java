package com.cloudsherpas.droolsample.service.auth;


import com.cloudsherpas.droolsample.api.resource.UserResource;

import java.util.Date;

public interface HttpRequestAuthorizationService {

    String generateToken(Date expiry, UserResource user, String origin);

    String getUsernameFromToken(String token, String origin);

    boolean isTokenValid(String token, String origin, String username);

}
