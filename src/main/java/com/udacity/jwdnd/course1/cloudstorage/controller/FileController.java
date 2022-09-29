package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.FileMapperService;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersMapperService;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Configuration
@RequestMapping("/upload-file")
public class FileController {
    private FileMapperService fileMapperService;
    private UsersMapperService usersMapperService;
    public FileController(FileMapperService fileMapperService, UsersMapperService usersMapperService){
        this.fileMapperService = fileMapperService;
        this.usersMapperService = usersMapperService;
    }
    @PostMapping
    public String uploadFile(Principal principal, @RequestParam("fileUpload") MultipartFile multipartFile, Model model, @ModelAttribute(name = "notes") Notes notes, @ModelAttribute(name = "creds") Credentials creds) throws IOException {
        Integer currentUserID = usersMapperService.getUsersMapper().getUserByUsername(principal.getName()).getUserid();
        try {
            if (multipartFile.getSize() > 9500000){
                throw new Exception("File is too large");
            }
            Files newFile = new Files();
            newFile.setContenttype(multipartFile.getContentType());
            newFile.setFiledata(multipartFile.getBytes());
            newFile.setUserid(currentUserID);
            newFile.setFilename("" + multipartFile.getName() + "_" + multipartFile.getOriginalFilename());
            newFile.setFilesize(multipartFile.getSize());
            fileMapperService.saveFile(newFile);
            List<Files> userFiles = fileMapperService.getUserFilesByUserId(currentUserID);
            model.addAttribute("userFiles", userFiles);
            System.out.println("File Upload: originalFileName:" + multipartFile.getOriginalFilename() + ";filesize:" + newFile.getFilesize());
            model.addAttribute("success", "success");
        }catch (Exception exception){
            System.out.println("file is too large!!");
        }
        return "home";
    }
}
