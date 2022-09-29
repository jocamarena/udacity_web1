package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {
    @Select("select top 1 * from Notes where userid = #{userid}")
    Notes getTopNoteByUserid(Integer userid);

    @Select("select * from Notes where userid = #{userid}")
    List<Notes> getNotesByUserid(Integer userid);

    @Update("update Notes set notetitle = #{notetitle}, notedescription = #{notedescription} where noteid = #{noteid}")
    Integer updateNoteByNoteID(Integer noteid);

    @Delete("delete from Notes where noteid = #{noteid}")
    void deleteNoteByID(Integer noteid);

    @Insert("insert into Notes (notetitle, notedescription, userid) values (#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    Integer saveNote(Notes note);
}
