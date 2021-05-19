package ru.itlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itlab.dto.UserDto;
import ru.itlab.services.UserRestServiceImpl;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    UserRestServiceImpl userRestService;

    @GetMapping("/restUsers")
    @ResponseBody
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok(userRestService.getAllUsers());
    }

    @PostMapping("/restUsers")
    @ResponseBody
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto user){
        return ResponseEntity.ok(userRestService.addUser(user));
    }

    @PutMapping("/restUsers/{user-id}")
    @ResponseBody
    public ResponseEntity<UserDto> updateUsr(@PathVariable("user-id") int userId, @RequestBody UserDto user){
        return ResponseEntity.ok(userRestService.updUser(userId, user));
    }

    @DeleteMapping("/restUsers/{user-id}")
    @ResponseBody
    public ResponseEntity<?> deleteUsr(@PathVariable("user-id") int userId){
        userRestService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}