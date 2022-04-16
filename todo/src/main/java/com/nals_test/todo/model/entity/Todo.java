package com.nals_test.todo.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "todo")
@Getter
@Setter
@NoArgsConstructor
public class Todo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "work_name", columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4")
    private String workName;

    @Column(name = "starting_date")
    private String startingDate;
    @Column(name = "ending_date")
    private String endingDate;

    @Column(name = "status", columnDefinition = "integer default 0")
    private Integer status;

    @Column(name = "flag_delete", columnDefinition = "boolean default false")
    private boolean flagDelete;
}
