package com.cloudsherpas.droolsample.api.endpoint;

import com.cloudsherpas.droolsample.api.resource.AuthorizationResource;
import com.cloudsherpas.droolsample.dto.UserDTO;
import com.cloudsherpas.droolsample.dto.UserDetailsDTO;
import com.cloudsherpas.droolsample.service.AuthenticationService;
import com.cloudsherpas.droolsample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController(value = "/user")
public class UserEndpoint {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authenticate",
                    method = RequestMethod.POST)
    public AuthorizationResource authenticationRequest(@RequestBody final UserDetailsDTO userDetails,
                                                       final HttpServletRequest request) {
        return authenticationService.authenticate(userDetails, request);
    }

    @RequestMapping(value = "/create",
                    method = RequestMethod.POST)
    public void createUser(@RequestBody UserDTO userDTO) throws AuthenticationException {
        userService.createUser(userDTO);
    }
}
