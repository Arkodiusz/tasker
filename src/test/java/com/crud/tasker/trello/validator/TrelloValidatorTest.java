package com.crud.tasker.trello.validator;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.crud.tasker.domain.TrelloBoard;
import com.crud.tasker.domain.TrelloCard;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TrelloValidatorTest {

    @Autowired
    TrelloValidator trelloValidator;

    @Test
    void testValidateTrelloBoards() {
        //Given
        List<TrelloBoard> boards = Arrays.asList(
                new TrelloBoard("1", "test", null),
                new TrelloBoard("2", "board2", null),
                new TrelloBoard("3", "board3", null),
                new TrelloBoard("4", "Test", null)
        );
        //When
        //Then
        assertEquals(2, trelloValidator.validateTrelloBoards(boards).size());
    }

    @Test
    void testValidateCard() {
        // Given
        Logger fooLogger = (Logger) LoggerFactory.getLogger(TrelloValidator.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        fooLogger.addAppender(listAppender);

        //When
        trelloValidator.validateCard(new TrelloCard("test", "desc1", "top", "list1"));
        trelloValidator.validateCard(new TrelloCard("card", "desc1", "top", "list1"));

        //Then
        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals("Someone is testing my application!", logsList.get(0)
                .getMessage());
        assertEquals(Level.INFO, logsList.get(0)
                .getLevel());

        assertEquals("Seems that my application is used in proper way.", logsList.get(1)
                .getMessage());
        assertEquals(Level.INFO, logsList.get(1)
                .getLevel());
    }
}