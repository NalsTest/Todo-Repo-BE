package com.nals_test.todo.repositories;

import com.nals_test.todo.model.entity.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface WorkRepository extends JpaRepository<Work, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE work \n" +
            "SET flag_delete = 1 \n" +
            "WHERE id = :id", nativeQuery = true)
    void deleteWork(@Param("id") Integer id);

    @Query(value = "SELECT work.id, work.ending_date, work.starting_date, work.flag_delete, work.work_name, work.status \n" +
            "FROM work \n" +
            "WHERE work.id = :id AND work.flag_delete = 0; ", nativeQuery = true)
    Work getWorkById(@Param("id") Integer id);

    @Query(value = "SELECT work.id, work.ending_date, work.starting_date, work.flag_delete, work.work_name, work.status \n" +
            "FROM work \n" +
            "WHERE work.flag_delete = 0 ",
            countQuery = "SELECT count(work.id) " +
                    "FROM work \n" +
                    "WHERE work.flag_delete = 0;",
            nativeQuery = true)
    Page<Work> getAllWorks(Pageable pageable);

    @Query(value = "SELECT work.id, work.ending_date, work.starting_date, work.flag_delete, work.work_name, work.status\n" +
            "FROM work WHERE (:workName IS NULL OR work.work_name LIKE %:workName%)\n" +
            "AND (:status IS NULL    OR work.status = :status)\n" +
            "AND (:startingDate IS NULL  OR work.starting_date = :startingDate )\n" +
            "AND ( :endingDate IS NULL  OR work.ending_date = :endingDate ) " +
            "AND work .flag_delete = 0 ",
            countQuery = "SELECT count(work.id) " +
                    "FROM todo WHERE (:workName IS NULL OR work.work_name LIKE %:workName%)\n" +
                    "AND (:status IS NULL    OR work.status = :status)\n" +
                    "AND (:startingDate IS NULL  OR work.starting_date = :startingDate )\n" +
                    "AND ( :endingDate IS NULL  OR work.ending_date = :endingDate ) \n" +
                    "AND work.flag_delete = 0;",
            nativeQuery = true)
    Page<Work> searchWork(@Param("workName") String workName, @Param("status") Integer status, @Param("startingDate") String startingDate, @Param("endingDate") String endingDate, Pageable pageable);

    @Query(value = "SELECT work.id, work.ending_date, work.starting_date, work.flag_delete, work.work_name, work.status\n" +
            "FROM work WHERE (:from IS NULL OR work.starting_date >= :from) \n" +
            "AND (:to IS NULL OR work.starting_date <= :to) \n" +
            "AND work.flag_delete = 0 ",
            countQuery = "SELECT count(work.id) " +
                    "FROM work WHERE (:from IS NULL OR work.starting_date >= :from) \n" +
                    "AND (:to IS NULL OR work.starting_date <= :to) \n" +
                    "            AND work.flag_delete = 0 ;",
            nativeQuery = true)
    Page<Work> searchTodoByStartingDate(@Param("from") String from, @Param("to") String to, Pageable pageable);

    @Query(value = "SELECT work.id, work.ending_date, work.starting_date, work.flag_delete, work.work_name, work.status\n" +
            "FROM work WHERE (:from IS NULL OR work.ending_date >= :from) \n" +
            "AND (:to IS NULL OR work.ending_date <= :to) \n" +
            "AND work.flag_delete = 0 ",
            countQuery = "SELECT count(work.id) " +
                    "FROM work WHERE (:from IS NULL OR work.ending_date >= :from) \n" +
                    "AND (:to IS NULL OR work.ending_date <= :to) \n" +
                    "AND work.flag_delete = 0 ;",
            nativeQuery = true)
    Page<Work> searchWorkByEndingDate(@Param("from") String from, @Param("to") String to, Pageable pageable);
}
