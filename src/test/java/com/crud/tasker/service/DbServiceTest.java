package com.crud.tasker.service;

import com.crud.tasker.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DbServiceTest {

    @Autowired
    DbService dbService;

    @Test
    void testGetAllTasks() {
        //Given
        Task task1 = new Task(1L, "t1", "");
        Task task2 = new Task(2L, "t2", "");
        Task task3 = new Task(3L, "t3", "");

        //When
        Long id1 = dbService.saveTask(task1).getId();
        Long id2 = dbService.saveTask(task2).getId();
        Long id3 = dbService.saveTask(task3).getId();

        //Then
        assertEquals(3, dbService.getAllTasks().size());

        //Cleanup
        dbService.deleteTask(id1);
        dbService.deleteTask(id2);
        dbService.deleteTask(id3);
    }

    @Test
    void testGetTask() {
        //Given
        Task task1 = new Task(1L, "t1", "");
        Task task2 = new Task(2L, "t2", "");
        Task task3 = new Task(3L, "t3", "");

        //When
        Long id1 = dbService.saveTask(task1).getId();
        Long id2 = dbService.saveTask(task2).getId();
        Long id3 = dbService.saveTask(task3).getId();

        //Then
        assertTrue(dbService.getTask(id2).isPresent());

        //Cleanup
        dbService.deleteTask(id1);
        dbService.deleteTask(id2);
        dbService.deleteTask(id3);
    }
}
