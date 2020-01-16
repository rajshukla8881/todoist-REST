package com.example.todoist.Models;



import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Project {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    @Column(columnDefinition = "TEXT")
    String name;


    int parent;


    int projectOrder;


    int commentCount;



    public Project(String name) {
        this.name=name;
        this.commentCount=0;
        this.projectOrder=1;
    }







}
