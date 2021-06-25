package com.manjenko.testproject.controllers;

import com.manjenko.testproject.model.ToDoList;
import com.manjenko.testproject.model.User;
import com.manjenko.testproject.pojo.*;
import com.manjenko.testproject.repo.ListRepository;
import com.manjenko.testproject.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/test/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ListController {

    @Autowired
    ListRepository listRepository;

    @GetMapping("/lists")
    public Iterable<ToDoList> loadLists(){
        Iterable <ToDoList> lists = listRepository.findAll();
        return lists;
    }

    @PostMapping("/lists/add")
    public ResponseEntity<?> registerUser(@RequestBody ListCreateRequest listCreateRequest){

        ToDoList toDoList = new ToDoList(listCreateRequest.getText(),
                listCreateRequest.getIsDone());

        listRepository.save(toDoList);
        return ResponseEntity.ok(new MessageResponse("ToDoList CREATED"));
    }

    @GetMapping("/lists/{id}")
    public ArrayList blogText(@PathVariable(value = "id") long id){
        Optional<ToDoList> toDoList = listRepository.findById(id);
        ArrayList<ToDoList> result = new ArrayList<>();
        toDoList.ifPresent(result::add);
        return result;
    }

    @PostMapping("/lists/{id}/edit")
    public Iterable<ToDoList> blogPostUpdate(@PathVariable(value = "id") long id, @RequestBody ListCreateRequest listCreateRequest ){
        ToDoList toDoList = listRepository.findById(id).orElseThrow(IllegalStateException::new);
        toDoList.setText(listCreateRequest.getText());
        toDoList.setIsDone(listCreateRequest.getIsDone());
        listRepository.save(toDoList);
        Iterable <ToDoList> lists = listRepository.findAll();
        return lists;
    }

    @PostMapping("/lists/{id}/remove")
    public Iterable<ToDoList> blogPostDelete(@PathVariable(value = "id") long id){
        ToDoList post = listRepository.findById(id).orElseThrow(IllegalStateException::new);
        listRepository.delete(post);
        Iterable <ToDoList> lists = listRepository.findAll();
        return lists;
    }



}
