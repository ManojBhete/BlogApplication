package com.manojb.blog.payloads;

// import com.manojb.blog.entity.Comment;
import com.manojb.blog.entity.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 3, message = "userName must be of 4 characters")
    private  String name;
    @Email(message = "Email address is not valid!")
    private String email;
    @NotEmpty
    @Size(min = 3 , max = 10, message = "password must be in between  min 3 and maximum 10 characters")
    private String password;
    @NotEmpty
    private String about;
    // by my own logic
  //  private Set<Comment> comments = new HashSet<>();
  //  private List<Post> posts = new ArrayList<>();






}
