package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsMapperService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileMapperService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesMapperService;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersMapperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final NotesMapperService notesMapperService;
    private final CredentialsMapperService credentialsMapperService;
    private UsersMapperService usersMapperService;

    public HomeController(NotesMapperService notesMapperService, CredentialsMapperService credentialsMapperService, UsersMapperService usersMapperService){

        this.notesMapperService = notesMapperService;
        this.credentialsMapperService = credentialsMapperService;
        this.usersMapperService = usersMapperService;
    }
    @GetMapping
    public String getHome(Model model, @ModelAttribute(name = "notes") Notes notes,  @ModelAttribute(name = "creds") Credentials creds){
        return "home";
    }
    @PostMapping("/note")
    public String addNote(Model model, @ModelAttribute(name = "notes") Notes notes, @ModelAttribute(name = "creds") Credentials creds, Principal principal){
        Integer currentUserID = usersMapperService.getUsersMapper().getUserByUsername(principal.getName()).getUserid();
        notes.setUserid(currentUserID);
        notesMapperService.saveNote(notes);
        notesMapperService.getUserNotes(currentUserID);
        List<Notes> userNotes = notesMapperService.getUserNotes(currentUserID);
        model.addAttribute("userNotes", userNotes);
        System.out.println("Note: " + "title:" + notes.getNotetitle() + "; description:" + notes.getNotedescription() + "; noteid:" + notes.getNoteid() + "; userid:" + notes.getUserid());
        return "home";
    }

    /*@GetMapping(value = "/delete")
    public String  deleteEmployee(@RequestParam(name = "noteid", required = true, defaultValue = "1") String noteid, Model model, @ModelAttribute(name = "notes") Notes notes, @ModelAttribute(name = "creds") Credentials creds, Principal principal) {
        Integer id = Integer.parseInt(noteid);
        notesMapperService.deleteNoteById(id);
        return "redirect:home";
    }*/
    @PostMapping("/credential")
    public String addCredential(Model model, @ModelAttribute(name = "notes") Notes notes, @ModelAttribute(name = "creds") Credentials creds, Principal principal){
        Integer currentUserID = usersMapperService.getUsersMapper().getUserByUsername(principal.getName()).getUserid();
        creds.setUserid(currentUserID);
        credentialsMapperService.saveCredentials(creds);
        List<Credentials> credentials = credentialsMapperService.getUserCredentials(currentUserID);
        System.out.println("userCreds size: " + credentials.size());
        model.addAttribute("userCreds", credentials);
        System.out.println("credentials size: " + credentials.size() + "; userid:" + creds.getUserid() + ";currentUserID:" + currentUserID);
        System.out.println("Credential: " + "id:" + creds.getCredentialid() + "; user:" + creds.getUsername());
        return "home";
    }
    @RequestMapping(value={"*"}, method={RequestMethod.GET, RequestMethod.POST})
    public String allRequests(Model model, @ModelAttribute(name = "notes") Notes notes, @ModelAttribute(name = "creds") Credentials creds, Principal principal) {
        return "home";
    }
}
