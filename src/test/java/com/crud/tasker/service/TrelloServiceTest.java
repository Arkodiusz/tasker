package com.crud.tasker.service;

import com.crud.tasker.domain.*;
import com.crud.tasker.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TrelloServiceTest {

    @MockBean
    TrelloClient trelloClient;

    @Autowired
    TrelloService trelloService;

    @Test
    void testFetchTrelloBoards() {
        //Given
        List<TrelloBoardDto> list = new ArrayList<>();
        list.add(new TrelloBoardDto());

        //When
        when(trelloClient.getTrelloBoards()).thenReturn(list);

        //Then
        assertEquals(trelloService.fetchTrelloBoards(), list);
    }

    @Test
    void testCreateTrelloCard() {
        //Given
        TrelloCardDto card = new TrelloCardDto();
        CreatedTrelloCardDto createdCard = new CreatedTrelloCardDto();

        //When
        when(trelloClient.createNewCard(any(TrelloCardDto.class))).thenReturn(createdCard);

        //Then
        assertEquals(trelloService.createTrelloCard(card), createdCard);
    }
}