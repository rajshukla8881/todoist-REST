package com.example.todoist.model;




import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "projectId")
    List<Section> sectionList;

    @OneToMany
    List<Task> taskList;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "projectId")
    List<Comment> commentList;


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
