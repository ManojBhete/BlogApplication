package com.manojb.blog.controller;

import com.manojb.blog.entity.User;
import com.manojb.blog.payloads.ApiResponse;
import com.manojb.blog.payloads.UserDto;
import com.manojb.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;

    // create user
    @PostMapping("/")
    // here i have taken userDto because this is one i want to fetch and save too
    public ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto userDto )
    {
       UserDto createUserDto  = this.userService.createUser(userDto);
       return new ResponseEntity<>(createUserDto , HttpStatus.CREATED);

    }
//   update
     @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser( @Valid @RequestBody UserDto userDto, @PathVariable  int userId )
     {
        UserDto updateUserDto = this.userService.updateUser(userDto,userId);

        return ResponseEntity.ok(updateUserDto);
     }

     // delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser( @PathVariable("userId") int userId )
    {
        this.userService.deleteUser(userId);
        return new  ResponseEntity( new ApiResponse("user deleted successfully", true), HttpStatus.OK);
    }

    // get all
      @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    // get single user

     @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSUser(@PathVariable int userId)
    {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }




}
