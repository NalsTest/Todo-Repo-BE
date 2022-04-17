package com.nals_test.todo.services;


import com.nals_test.todo.model.entity.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface WorkService {
    void add(Work work);

    void edit(Work work);

    Page<Work> findAll(Pageable pageable);

    void delete(Integer id);

    Work findById(Integer id);

    Page<Work> searchWork(String workName, Integer status, String startingDate, String endingDate, Pageable pageable);

    Page<Work> searchWorkByStartingDate(String from, String to, Pageable pageable);

    Page<Work> searchWorkByEndingDate(String from, String to, Pageable pageable);
}
