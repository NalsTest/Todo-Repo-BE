package com.nals_test.todo.repositories;

import com.nals_test.todo.model.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            "WHERE id = :id", nativeQuery = true)
    void deleteTodo(@Param("id") Integer id);

    @Query(value = "SELECT todo.id, todo.ending_date, todo.starting_date, todo.flag_delete, todo.work_name, todo.status \n" +
            "FROM todo \n" +
            "WHERE todo.id = :id AND todo.flag_delete = 0; ", nativeQuery = true)
    Todo getTodoById(@Param("id") Integer id);

    @Query(value = "SELECT todo.id, todo.ending_date, todo.starting_date, todo.flag_delete, todo.work_name, todo.status \n" +
            "FROM todo \n" +
            "WHERE todo.flag_delete = 0 ",
            countQuery = "SELECT count(todo.id) " +
                    "FROM todo \n" +
                    "WHERE todo.flag_delete = 0;",
            nativeQuery = true)
    Page<Todo> getAllTodo(Pageable pageable);

    @Query(value = "SELECT todo.id, todo.ending_date, todo.starting_date, todo.flag_delete, todo.work_name, todo.status\n" +
            "FROM todo WHERE (:workName IS NULL OR todo.work_name LIKE %:workName%)\n" +
            "            AND (:status IS NULL    OR todo.status = :status)\n" +
            "            AND (:startingDate IS NULL  OR todo.starting_date >= :startingDate )\n" +
            "            AND ( :endingDate IS NULL  OR todo.ending_date <= :endingDate ) " +
            "            AND todo.flag_delete = 0 ",
            countQuery = "SELECT count(todo.id) " +
                    "FROM todo WHERE (:workName IS NULL OR todo.work_name LIKE %:workName%)\n" +
                    "            AND (:status IS NULL    OR todo.status = :status)\n" +
                    "            AND (:startingDate IS NULL  OR todo.starting_date >= :startingDate )\n" +
                    "            AND ( :endingDate IS NULL  OR todo.ending_date <= :endingDate ) \n" +
                    "            AND todo.flag_delete = 0;",
            nativeQuery = true)
    Page<Todo> searchTodo(@Param("workName") String workName,@Param("status") Integer status,@Param("startingDate") String startingDate,@Param("endingDate") String endingDate, Pageable pageable);

    @Query(value = "SELECT todo.id, todo.ending_date, todo.starting_date, todo.flag_delete, todo.work_name, todo.status\n" +
            "FROM todo WHERE todo.starting_date = :startingDate \n" +
            "            AND todo.flag_delete = 0 ",
            countQuery = "SELECT count(todo.id) " +
                    "FROM todo WHERE todo.starting_date = :startingDate \n" +
                    "            AND todo.flag_delete = 0 ;",
            nativeQuery = true)
    Page<Todo> searchTodoByStartingDate(@Param("startingDate") String startingDate, Pageable pageable);

    @Query(value = "SELECT todo.id, todo.ending_date, todo.starting_date, todo.flag_delete, todo.work_name, todo.status\n" +
            "FROM todo WHERE todo.ending_date = :endingDate \n" +
            "            AND todo.flag_delete = 0 ",
            countQuery = "SELECT count(todo.id) " +
                    "FROM todo WHERE todo.ending_date = :endingDate \n" +
                    "            AND todo.flag_delete = 0 ;",
            nativeQuery = true)
    Page<Todo> searchTodoByEndingDate(@Param("endingDate") String endingDate, Pageable pageable);
}
