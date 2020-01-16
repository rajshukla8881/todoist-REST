package com.example.todoist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Getter
    @Setter
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
        this.name = name;
        this.commentCount = 0;
        this.projectOrder = 1;
    }
}