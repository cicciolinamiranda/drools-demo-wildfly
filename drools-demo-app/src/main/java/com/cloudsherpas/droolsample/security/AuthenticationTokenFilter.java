package com.cloudsherpas.droolsample.security;

import com.cloudsherpas.droolsample.service.UserAuthDetailsService;
import com.cloudsherpas.droolsample.service.auth.JwtHttpRequestAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthenticationTokenFilter extends
        UsernamePasswordAuthenticationFilter {

    private String tokenHeader = "X-AUTH-TOKEN";

    @Autowired
    private UserAuthDetailsService userAuthDetailsService;

    @Autowired
    private JwtHttpRequestAuthorizationService jwtService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader(tokenHeader);
        String username = jwtService.getUsernameFromToken(authToken, httpRequest.getRemoteAddr());

        if (username != null && SecurityContextHolder.getContext()
                                                     .getAuthentication() == null) {
            if (jwtService.isTokenValid(authToken, httpRequest.getRemoteAddr(), username)) {
                UserDetails userDetails = userAuthDetailsService
                        .loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource()
                                                  .buildDetails(httpRequest));
                SecurityContextHolder.getContext()
                                     .setAuthentication(
                                             authentication);
            }
        }

        chain.doFilter(request, response);
    }

}
