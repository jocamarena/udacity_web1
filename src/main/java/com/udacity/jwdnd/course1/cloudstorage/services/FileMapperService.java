package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileMapperService {
    private final FilesMapper filesMapper;
    public FileMapperService(FilesMapper filesMapper){
        this.filesMapper = filesMapper;
    }
    public Integer saveFile(Files file){
        return filesMapper.saveFile(file);
    }
    public List<Files> getUserFilesByUserId(Integer userid){
        return filesMapper.getFilesByUserID(userid);
    }
    public void deleteFileById(Integer fileid){
        filesMapper.deleteFileById(fileid);
    }
}
