package com.crud.tasker.mapper;

import com.crud.tasker.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloMapperTest {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    void testMapToList() {
        //Given
        List<TrelloListDto> dtoLists = Arrays.asList(
                new TrelloListDto("1", "list1", false),
                new TrelloListDto("2", "list2", false),
                new TrelloListDto("3", "list3", true)
        );

        //When
        List<TrelloList> lists = trelloMapper.mapToList(dtoLists);

        //Then
        assertAll(
                () -> assertEquals(3, lists.size()),

                () -> assertEquals("1", lists.get(0).getId()),
                () -> assertEquals("list1", lists.get(0).getName()),
                () -> assertFalse(lists.get(0).isClosed()),

                () -> assertEquals("3", lists.get(2).getId()),
                () -> assertEquals("list3", lists.get(2).getName()),
                () -> assertTrue(lists.get(2).isClosed())
        );
    }

    @Test
    void testMapToListDto() {
        //Given
        List<TrelloList> lists = Arrays.asList(
                new TrelloList("1", "list1", false),
                new TrelloList("2", "list2", false),
                new TrelloList("3", "list3", true)
        );

        //When
        List<TrelloListDto> dtoLists = trelloMapper.mapToListDto(lists);

        //Then
        assertAll(
                () -> assertEquals(3, dtoLists.size()),

                () -> assertEquals("1", dtoLists.get(0).getId()),
                () -> assertEquals("list1", dtoLists.get(0).getName()),
                () -> assertFalse(dtoLists.get(0).isClosed()),

                () -> assertEquals("3", dtoLists.get(2).getId()),
                () -> assertEquals("list3", dtoLists.get(2).getName()),
                () -> assertTrue(dtoLists.get(2).isClosed())
        );
    }

    @Test
    void testMapToCard() {
        //Given
        TrelloCardDto dtoCard = new TrelloCardDto("card1", "description1", "top", "list1");

        //When
        TrelloCard card= trelloMapper.mapToCard(dtoCard);

        //Then
        assertAll(
                () -> assertEquals("card1", card.getName()),
                () -> assertEquals("description1", card.getDescription()),
                () -> assertEquals("top", card.getPos()),
                () -> assertEquals("list1", card.getListId())
        );
    }

    @Test
    void testMapToCardDto() {
        //Given
        TrelloCard card = new TrelloCard("card1", "description1", "top", "list1");

        //When
        TrelloCardDto dtoCard = trelloMapper.mapToCardDto(card);

        //Then
        assertAll(
                () -> assertEquals("card1", dtoCard.getName()),
                () -> assertEquals("description1", dtoCard.getDescription()),
                () -> assertEquals("top", dtoCard.getPos()),
                () -> assertEquals("list1", dtoCard.getListId())
        );
    }

    @Test
    void testMapToBoards() {
        //Given
        List<TrelloListDto> dtoLists1 = Arrays.asList(
                new TrelloListDto("1", "list1", false),
                new TrelloListDto("2", "list2", false),
                new TrelloListDto("3", "list3", true)
        );

        List<TrelloListDto>  dtoLists2 = Arrays.asList(
                new TrelloListDto("4", "list4", false),
                new TrelloListDto("5", "list5", true),
                new TrelloListDto("6", "list6", false)
        );

        List<TrelloBoardDto> dtoBoards = Arrays.asList(
                new TrelloBoardDto("101", "board1", dtoLists1),
                new TrelloBoardDto("102", "board2", dtoLists2)
        );

        //When
        List<TrelloBoard> boards = trelloMapper.mapToBoards(dtoBoards);

        //Then
        assertAll(
                () -> assertEquals(2, boards.size()),

                () -> assertEquals("101", boards.get(0).getId()),
                () -> assertEquals("board1", boards.get(0).getName()),
                () -> assertTrue(boards.get(0).getLists().get(2).isClosed()),

                () -> assertEquals("102", boards.get(1).getId()),
                () -> assertEquals("board2", boards.get(1).getName()),
                () -> assertFalse(boards.get(1).getLists().get(2).isClosed())
        );
    }

    @Test
    void testMapToBoardsDto() {
        //Given
        List<TrelloList> lists1 = Arrays.asList(
                new TrelloList("1", "list1", false),
                new TrelloList("2", "list2", false),
                new TrelloList("3", "list3", true)
        );

        List<TrelloList>  lists2 = Arrays.asList(
                new TrelloList("4", "list4", false),
                new TrelloList("5", "list5", true),
                new TrelloList("6", "list6", false)
        );

        List<TrelloBoard> boards = Arrays.asList(
                new TrelloBoard("101", "board1", lists1),
                new TrelloBoard("102", "board2", lists2)
        );

        //When
        List<TrelloBoardDto> dtoBoards = trelloMapper.mapToBoardsDto(boards);

        //Then
        assertAll(
                () -> assertEquals(2, dtoBoards.size()),

                () -> assertEquals("101", dtoBoards.get(0).getId()),
                () -> assertEquals("board1", dtoBoards.get(0).getName()),
                () -> assertTrue(dtoBoards.get(0).getLists().get(2).isClosed()),

                () -> assertEquals("102", dtoBoards.get(1).getId()),
                () -> assertEquals("board2", dtoBoards.get(1).getName()),
                () -> assertFalse(dtoBoards.get(1).getLists().get(2).isClosed())
        );
    }

}