package com.nals_test.todo.services;

import com.nals_test.todo.model.entity.Work;
import com.nals_test.todo.repositories.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Override
    public void add(Work work) {
        if (work != null) {
            this.workRepository.save(work);
        }
    }

    @Override
    public void edit(Work work) {
        this.workRepository.save(work);
    }

    @Override
    public Page<Work> findAll(Pageable pageable) {
        return this.workRepository.getAllWorks(pageable);
    }

    @Override
    public void delete(Integer id) {
        this.workRepository.deleteWork(id);
    }

    @Override
    public Work findById(Integer id) {
        return this.workRepository.getWorkById(id);
    }

    @Override
    public Page<Work> searchWork(String workName, Integer status, String startingDate, String endingDate, Pageable pageable) {
        return this.workRepository.searchWork(workName, status, startingDate, endingDate, pageable);
    }

    @Override
    public Page<Work> searchWorkByStartingDate(String from, String to, Pageable pageable) {
        return this.workRepository.searchTodoByStartingDate(from, to, pageable);
    }

    @Override
    public Page<Work> searchWorkByEndingDate(String from, String to, Pageable pageable) {
        return this.workRepository.searchWorkByEndingDate(from, to, pageable);
    }
}
