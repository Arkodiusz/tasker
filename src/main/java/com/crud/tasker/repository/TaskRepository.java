package com.crud.tasker.repository;

import com.crud.tasker.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    @Override
    List<Task> findAll();

    @Override
    Optional<Task> findById(Long id);

    @Override
    Task save(Task task);

    @Override
    void deleteById(Long Id);
}