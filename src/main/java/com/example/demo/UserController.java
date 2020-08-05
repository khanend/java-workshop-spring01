package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @GetMapping("/users")
    public   List<UsersResponse> getAllUser(){
        List<UsersResponse> usersResponses = new ArrayList<>();
        usersResponses.add(new UsersResponse(1,"User 1"));
        usersResponses.add(new UsersResponse(2,"User 2"));
        usersResponses.add(new UsersResponse(3,"User 3"));
        return usersResponses;
    }
//    @GetMapping("/users/{id}")
//    public UsersResponse getUserById(@PathVariable int id){
//        return new UsersResponse(id, "User"+id);
//    }
//
    @GetMapping("/users/")
    public  String  getUserByString(@RequestParam(required = false ,defaultValue = "2") String page ,
                                    @RequestParam(required = false, defaultValue = "15" ) String item_per_page
                                    ){
            return   "Page: " + page ;
    }
}
