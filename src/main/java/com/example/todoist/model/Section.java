package com.example.todoist.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
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

    public Section(String name, Integer project_id, Integer order) {
        this.name=name;
        this.projectId=project_id;
        this.sectionOrder=order;
    }
}
