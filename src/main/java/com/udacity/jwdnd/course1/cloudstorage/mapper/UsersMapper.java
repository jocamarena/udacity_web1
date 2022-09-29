package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UsersMapper {
    @Select("select * from Users where username = #{username}")
    Users getUserByUsername(String username);

    @Insert("insert into Users ( firstname, lastname, username, salt, password) values ( #{firstname}, #{lastname}, #{username}, #{salt}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "userid", keyColumn = "userid")
    Integer saveUser(Users users);
}
