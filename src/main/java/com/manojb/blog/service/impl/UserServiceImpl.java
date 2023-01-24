package com.manojb.blog.service.impl;
// using JSR 380 bean validation
import com.manojb.blog.entity.User;
import com.manojb.blog.exception.ResourceNotFoundException;
import com.manojb.blog.payloads.UserDto;
import com.manojb.blog.repository.UserRepository;
import com.manojb.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

          // we took data of dtoToUser into user
         User user = this.dtoToUser(userDto);
         // now we are going to save data of user by using repository why ? because userDto doesn't have repository
        User savedUser =  this.userRepository.save(user);

         // converting user into userDto and returning
        return this.userToDto(savedUser);


    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId)
    {
       // I am throwing exception & getting exact id of user
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id",userId ));

        // setting field of user by userDto

        user.setName(userDto.getName());
       user.setEmail(userDto.getEmail());
      user.setAbout(userDto.getAbout());
      user.setPassword(userDto.getPassword());
// entire updated user fields saved into updatedUser
        User updatedUser  = this.userRepository.save(user);
        // converting updated fields from user to userDto because I can fetch from only userDto
       UserDto userDto1  = this.userToDto(updatedUser);
         return userDto1;

    }

    @Override
    public UserDto getUserById(Integer userId)
    {

        User user =  this.userRepository.findById(userId).orElseThrow( () -> new ResourceNotFoundException("User", "Id", userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers()
    {
        // took entire list and saved into users
        List<User> users =  this.userRepository.findAll();
        // took entire list of users into userDtos
         List<UserDto> userDtos =  users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());


        return userDtos;
    }

    @Override
    public void deleteUser(int userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        this.userRepository.delete(user);

    }

    // taking entire data of UserDto into user
    // I'm not using this because i'm using model mapper
    public User dtoToUser(UserDto userDto)
    {
        // I'm not using old way  because i'm using model mapper

//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
//        return user;
        // using model mapper

        User user = this.modelMapper.map(userDto, User.class); // .map(from, to where)
        return user;
    }
    // taking entire data of User into userDto

    public UserDto userToDto(User user)
    {
        // I'm not using old way  because i'm using model mapper

//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
//        return userDto;

        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return  userDto;

    }


}
