package com.woonjin.blog.web;

import com.woonjin.blog.application.service.BoardService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    private final BoardService boardService;

    public BoardController(
        BoardService boardService
    ){
        this.boardService = boardService;
    }
}
