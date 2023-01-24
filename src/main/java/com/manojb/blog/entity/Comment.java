package com.manojb.blog.entity;

import lombok.*;

import javax.persistence.*;

@Entity
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @ManyToOne
    private Post post;

    // by my own logic

//    @ManyToOne
//    private User user;



}
