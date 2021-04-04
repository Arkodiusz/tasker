package com.crud.tasker.controller;

import com.crud.tasker.domain.CreatedTrelloCardDto;
import com.crud.tasker.domain.TrelloBoardDto;
import com.crud.tasker.domain.TrelloCardDto;
import com.crud.tasker.service.TrelloService;
import com.crud.tasker.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {

    private final TrelloClient trelloClient;
    private final TrelloService trelloService;

    @GetMapping("getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloService.fetchTrelloBoards();
    }

    @PostMapping("createTrelloCard")
    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloService.createTrelloCard(trelloCardDto);
    }
}