package com.example.todoist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "due_table")
public class Due implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Value("${some.key:}")
    private String string;

    @Value("${some.key:}")
    private String date;

    @Value("${some.key:}")
    private String dateTime;

    @Value("${some.key:}")
    private String timezone;
}