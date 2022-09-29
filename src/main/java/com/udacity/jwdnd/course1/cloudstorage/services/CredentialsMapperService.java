package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsMapperService {
    private final CredentialsMapper credentialsMapper;
    public CredentialsMapperService(CredentialsMapper credentialsMapper){
        this.credentialsMapper = credentialsMapper;
    }
    public Integer saveCredentials(Credentials credentials){
        return credentialsMapper.saveCredential(credentials);
    }
    public Credentials getTopUserCredential(Integer userid){
        return credentialsMapper.getTopCredentialByUserid(userid);
    }
    public List<Credentials> getUserCredentials(Integer userid){
        return credentialsMapper.getCredentialsByUserid(userid);
    };
    public void deleteCredentialById(Integer id){
        credentialsMapper.deleteCredentialById(id);
    }
}
