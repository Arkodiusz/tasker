package com.crud.tasker.controller;

import com.crud.tasker.domain.*;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskControllerTest.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskController taskController;

    @Test
    void shouldFetchAllTasks() throws Exception {
        //Given
        TaskDto taskDto1 = new TaskDto(1L, "title1", "content1");
        TaskDto taskDto2 = new TaskDto(2L, "title2", "content2");
        TaskDto taskDto3 = new TaskDto(3L, "title3", "content3");
        List<TaskDto> taskDtoList = List.of(taskDto1, taskDto2, taskDto3);

        when(taskController.getTasks()).thenReturn(taskDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("title1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("content1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].title", Matchers.is("title3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].content", Matchers.is("content3")));
    }

    @Test
    void shouldReturnTask() throws Exception, TaskNotFoundException {
        // Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        when(taskController.getTask(1L)).thenReturn(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content")));
    }

    @Test
    void shouldReturnOkWhenDeletingTask() throws Exception {
        //Given & When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldReturnTaskWhenUpdatingTask() throws Exception {
        // Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");

        when(taskController.updateTask(any(TaskDto.class))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content")));
    }

    @Test
    void shouldReturnOkWhenCreatingTask() throws Exception {
        // Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}