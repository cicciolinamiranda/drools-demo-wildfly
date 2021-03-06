package com.cloudsherpas.droolsample.service;

import com.cloudsherpas.droolsample.api.resource.AuthorizationResource;
import com.cloudsherpas.droolsample.api.resource.UserResource;
import com.cloudsherpas.droolsample.api.resource.AuthenticationResource;
import com.cloudsherpas.droolsample.service.auth.JwtHttpRequestAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author RMPader
 */
@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAuthDetailsService userAuthDetailsService;

    @Autowired
    private JwtHttpRequestAuthorizationService jwtService;

    public AuthorizationResource authenticate(AuthenticationResource userDetails, HttpServletRequest request) {
        // Perform the authentication
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), userDetails.getPassword()));
        SecurityContextHolder.getContext()
                             .setAuthentication(authentication);

        // Reload password post-authentication so we can generate token
        UserResource userResource = this.userAuthDetailsService.loadUserDTOByUsername(userDetails.getUsername());
        String token = jwtService.generateToken(new Date(), userResource, request.getRemoteAddr());
        AuthorizationResource response = new AuthorizationResource();
        response.setRole(userResource.getRole());
        response.setToken(token);
        return response;
    }

}
