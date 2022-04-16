package com.nals_test.todo.repositories;

import com.nals_test.todo.model.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE todo \n" +
            "SET flag_delete = 1 \n" +
            "WHERE id = :id", nativeQuery=true)
    void deleteTodo(@Param("id") Integer id);
}
