package com.crud.tasker.service;

import com.crud.tasker.domain.Task;
import com.crud.tasker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {

    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Optional<Task> getTask(final Long taskId) {
        return repository.findById(taskId);
    }

    public Task saveTask(final Task task) {
        return repository.save(task);
    }

    public void deleteTask(final Long id) {
        repository.deleteById(id);
    }
}
