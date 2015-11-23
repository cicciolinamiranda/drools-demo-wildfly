package com.cloudsherpas.droolsample.api.endpoint;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cloudsherpas.droolsample.service.UserService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloudsherpas.droolsample.dto.UserDTO;
import com.cloudsherpas.droolsample.dto.UserDetailsDTO;
import com.cloudsherpas.droolsample.service.UserAuthDetailsService;
import com.cloudsherpas.droolsample.service.auth.JwtHttpRequestAuthorizationService;

@RestController
public class UserEndpoint {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserAuthDetailsService userAuthDetailsService;

    @Autowired
    private JwtHttpRequestAuthorizationService jwtService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> authenticationRequest(@RequestBody final UserDetailsDTO userDetails,
                          tapo                         final HttpServletRequest request) throws AuthenticationException {
        // Perform the authentication
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), userDetails.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-authentication so we can generate token
        UserDTO userDTO = this.userAuthDetailsService.loadUserDTOByUsername(userDetails.getUsername());
        String token = jwtService.generateToken(new Date(), userDTO, request.getRemoteAddr());

        Map<String, String> tokenJsonResponse = new HashMap<>();
        tokenJsonResponse.put("token", token);

        // Return the token
        return ResponseEntity.ok(tokenJsonResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/authorize", method = RequestMethod.GET)
    public ResponseEntity<?> authorize(final HttpServletRequest request) throws AuthenticationException {

        System.out.println("Auth: " + Arrays.toString(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()));
        return ResponseEntity.ok("Authorized");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(final HttpServletRequest request,
                                        @RequestBody UserDTO userDTO) throws AuthenticationException {
        userService.createUser(userDTO);
        return ResponseEntity.ok(HttpStatus.SC_OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> createUser(final HttpServletRequest request) throws AuthenticationException {
        return ResponseEntity.ok(HttpStatus.SC_OK);
    }
}
