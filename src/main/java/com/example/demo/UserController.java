package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public PagingResponse getAllUser(@RequestParam(defaultValue ="1") int page,
                                     @RequestParam(name = "item_per_page",defaultValue = "10") int itemPerPage){
        PagingResponse pagingResponse = new PagingResponse(page, itemPerPage);
        List<UsersResponse> usersResponseList = new ArrayList<>();
        Iterable<User> users = userRepository.findAll();
        for (User user:users){
            usersResponseList.add(new UsersResponse(user.getId(), user.getName(), user.getAge()));
        }
//        usersResponseList.add(new UsersResponse(1,"user1"));
//        usersResponseList.add(new UsersResponse(2,"user2"));
//        usersResponseList.add(new UsersResponse(3,"user3"));
        pagingResponse.setUsersResponseList(usersResponseList);
        return pagingResponse;
//        return usersResponseList;
    }

//    @GetMapping("/queryparam")
//    public String getQueryParam(@RequestParam(required = false, defaultValue ="1") int page,
//                                @RequestParam(required = false, defaultValue = "10") int item_per_page){
//        return "page: " + page + " item: " + item_per_page;
//    }


//    public UsersResponse[] getAllUser(){
//        UsersResponse[] usersResponses = new UsersResponse[2];
//        usersResponses[0] = new UsersResponse(1, "User 1");
//        usersResponses[1] = new UsersResponse(2, "User 2");
//        return usersResponses;
//    }

    @GetMapping("/users/{id}")
    public UsersResponse getUserById(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        return new UsersResponse(user.get().getId(), user.get().getName());
//        return new UsersResponse(id, "User " + id);
    }

    @PostMapping("/users")
    public UsersResponse createNewUser(@RequestBody NewUserRequest request){
        //validate input
        //create new user to DB
        User user = new User();
        user.setName(request.getName());
        user.setAge(request.getAge());
        user = userRepository.save(user);
        return new UsersResponse(user.getId(), user.getName() + user.getAge());
    }

    @PostMapping(path = "/users1")
    public String createNewUserWithFormData(NewUserRequest request){//map if the same
        return request.getName() + request.getAge();
    }
}