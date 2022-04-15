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

    private String workName;

    private String startingDate;

    private String endingDate;

    @Column(columnDefinition = "integer default 0")
    private Integer status;

    @Column(columnDefinition = "boolean default false")
    private boolean flagDelete;
}
