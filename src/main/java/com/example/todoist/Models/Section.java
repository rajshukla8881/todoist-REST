package com.example.todoist.Models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Section {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    int projectId;

    int sectionOrder;

    @Column(columnDefinition = "TEXT")
    String name;



    public Section(String name, Integer project_id) {
        this.name=name;
        this.projectId=project_id;
        this.sectionOrder=1;
    }
}
