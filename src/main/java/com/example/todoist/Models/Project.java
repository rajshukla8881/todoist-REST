package com.example.todoist.Models;



import lombok.*;

import javax.persistence.*;

@Entity

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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


    public Project(String name, Integer parent) {
        this.name=name;
        this.parent=parent;
        this.commentCount=0;
        this.projectOrder=1;
    }
}
