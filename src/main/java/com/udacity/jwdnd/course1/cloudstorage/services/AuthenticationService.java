package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class AuthenticationService implements AuthenticationProvider {
    private final UsersMapper userMapper;
    private final HashService hashService;
    private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
    public AuthenticationService(UsersMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("AuthenticationService:authenticate " + authentication.getName());
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Users user = userMapper.getUserByUsername(username);
        if (user != null) {
            String encodedSalt = user.getSalt();
            String hashedPassword = hashService.getHashedValue(password, encodedSalt);
            if (user.getPassword().equals(hashedPassword)) {
                usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
                return usernamePasswordAuthenticationToken;
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println("AuthenticationService:supports");
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken() {
        return usernamePasswordAuthenticationToken;
    }
}