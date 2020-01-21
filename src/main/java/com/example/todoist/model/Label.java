package com.example.todoist.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Label {

    @Column(columnDefinition = "TEXT")
    String name;
    int labelOrder;
    @Id
    private int id;

    public Label(String name) {
        this.name = name;
        this.labelOrder = 10;
    }
}