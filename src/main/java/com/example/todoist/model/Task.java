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
    private Boolean completed;

    @OneToMany
    private List<Label> labelIds;

    private int parent;

    private Integer orders;

    private int indent;

    private int priority;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private Due due;

    @Column(nullable = false)
    private String url;

    @Column(nullable = true)
    private int commentCount;
}