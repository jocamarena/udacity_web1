package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {
    @Select("select top 1 * from Credentials where userid = #{userid}")
    Credentials getTopCredentialByUserid(Integer userid);

    @Select("select * from Credentials where userid = #{userid}")
    List<Credentials> getCredentialsByUserid(Integer userid);

    @Insert("insert into Credentials (url, username, key, password, userid) values (#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    Integer saveCredential(Credentials credentials);

    @Delete("delete from Credentials where credentialid = #{credentialid}")
    void deleteCredentialById(Integer credentialid);
}
