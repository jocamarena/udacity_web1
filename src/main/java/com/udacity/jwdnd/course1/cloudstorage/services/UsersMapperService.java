package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UsersMapperService {
    private final UsersMapper usersMapper;
    private final HashService hashService;
    public UsersMapperService(UsersMapper usersMapper, HashService hashService){
        this.hashService = hashService;
        this.usersMapper = usersMapper;
    }
    public boolean isUsernameUnique(String username){
        return usersMapper.getUserByUsername(username) == null;
    }
    public Integer saveUser(Users user){
        Integer userInt;
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        user.setPassword(hashedPassword);
        user.setSalt(encodedSalt);
        if(usersMapper.getUserByUsername(user.getUsername()) == null) {
            userInt = usersMapper.saveUser(user);
            System.out.println("User Saved");
        } else {
            userInt = 1;
            System.out.println("User found");
        }
        System.out.println("GetUserByUsername return userid: " + usersMapper.getUserByUsername(user.getUsername()).getUserid());
        System.out.println("User: salt:" + user.getSalt() + ";pw:" + user.getPassword());
        return userInt;
    }

    public UsersMapper getUsersMapper() {
        return usersMapper;
    }
}
