package com.udacity.jwdnd.course1.cloudstorage.mapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {
    @Select("select top 1 * from Files where userid = #{userid}")
    Files getTopFileByUserId(Integer userid);

    @Select("select * from Files where userid = #{userid}")
    List<Files> getFilesByUserID(Integer userid);

    @Insert("insert into Files (filename, contenttype, filesize, userid, filedata) values (#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    Integer saveFile(Files file);

    @Delete("delete from Files where fileid = #{fileid}")
    void deleteFileById(Integer fileid);
}
