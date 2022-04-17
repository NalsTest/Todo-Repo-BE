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
public class Work implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "work_name", columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4")
    private String workName;

    @Column(name = "starting_date", columnDefinition = "date")
    private String startingDate;

    @Column(name = "ending_date", columnDefinition = "date")
    private String endingDate;

    // Trường status sử dụng kiểu Integer có 3 giá trị 0,1,2 tương ứng với Planing, Doing, Complete
    @Column(name = "status", columnDefinition = "integer default 0")
    private Integer status;

    // Không xoá trực tiếp dữ liệu trong DB mà sử dụng trường này để xoá, mặc định là false khi chuyển sang true là xoá
    @Column(name = "flag_delete", columnDefinition = "boolean default false")
    private boolean flagDelete;
}
