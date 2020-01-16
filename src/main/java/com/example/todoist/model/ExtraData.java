package com.example.todoist.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExtraData {

    private String content;

    @Id
    private String dueDate;
    private String lastDueDate;
}