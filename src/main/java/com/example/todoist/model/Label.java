package com.example.todoist.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Label {

    @Id
    private int id;


    @Column(columnDefinition = "TEXT")
    String name;

    int labelOrder;

    public Label(String name) {
        this.name=name;
        this.labelOrder=10;
    }
}
