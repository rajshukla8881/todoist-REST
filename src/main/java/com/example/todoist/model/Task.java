package com.example.todoist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "task_table")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int projectId;
    @Column(nullable = false)
    private int sectionId;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private boolean completed;
    @ManyToMany
    @Column(name = "label_ids")
    private List<Label> labelList;

    private int parent;
    private int orders; //order is some keyword in sql so unable to create the table with it.
    private int priority;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private Due due;
    @Column(nullable = true)
    private String url;
    @Column(nullable = true)
    private int comment_count;
}