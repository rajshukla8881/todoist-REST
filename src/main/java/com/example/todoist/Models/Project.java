package com.example.todoist.Models;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Project {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Getter
    @Setter
    @Column(columnDefinition = "TEXT")
    String name;

    @Getter
    @Setter
    int parent;

    @Getter
    @Setter
    int projectOrder;

    @Getter
    @Setter
    int commentCount;



    public Project(String name) {
        this.name=name;
        this.commentCount=0;
        this.projectOrder=1;
    }







}
