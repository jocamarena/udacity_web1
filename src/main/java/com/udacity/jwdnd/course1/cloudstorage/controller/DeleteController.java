package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsMapperService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileMapperService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesMapperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class DeleteController {
    private final NotesMapperService notesMapperService;
    private final CredentialsMapperService credentialsMapperService;
    private final FileMapperService fileMapperService;
    public DeleteController(NotesMapperService notesMapperService, CredentialsMapperService credentialsMapperService, FileMapperService fileMapperService){
        this.notesMapperService = notesMapperService;
        this.credentialsMapperService = credentialsMapperService;
        this.fileMapperService = fileMapperService;
    }
    @GetMapping(value = "/deletenote")
    public String  deleteNote(@RequestParam(name = "noteid", required = true, defaultValue = "1") String noteid, Model model, @ModelAttribute(name = "notes") Notes notes, @ModelAttribute(name = "creds") Credentials creds, Principal principal) {
        Integer id = Integer.parseInt(noteid);
        notesMapperService.deleteNoteById(id);
        return "home";
    }
    @GetMapping(value = "/deletecred")
    public String  deleteCredential(@RequestParam(name = "credid", required = true, defaultValue = "1") String credid, Model model, @ModelAttribute(name = "notes") Notes notes, @ModelAttribute(name = "creds") Credentials creds, Principal principal) {
        Integer id = Integer.parseInt(credid);
        credentialsMapperService.deleteCredentialById(id);
        return "home";
    }
    @GetMapping(value = "/deletefile")
    public String  deleteFile(@RequestParam(name = "fileid", required = true, defaultValue = "1") String fileid, Model model, @ModelAttribute(name = "notes") Notes notes, @ModelAttribute(name = "creds") Credentials creds, Principal principal) {
        Integer id = Integer.parseInt(fileid);
        fileMapperService.deleteFileById(id);
        System.out.println("File Deleted");
        return "home";
    }
}
