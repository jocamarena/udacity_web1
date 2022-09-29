package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotesMapperService {
    private final NotesMapper notesMapper;
    public NotesMapperService(NotesMapper notesMapper){
        this.notesMapper = notesMapper;
    }

    public Integer saveNote(Notes note){
        return notesMapper.saveNote(note);
    }

    public Notes getTopUserNote(Integer userID){
         return notesMapper.getTopNoteByUserid(userID);
    }
    public List<Notes> getUserNotes(Integer userID){
        return notesMapper.getNotesByUserid(userID);
    }

    public Integer updateNoteById(Integer noteid){
        return notesMapper.updateNoteByNoteID(noteid);
    }
    public void deleteNoteById(Integer noteid){
        notesMapper.deleteNoteByID(noteid);
    }
}
