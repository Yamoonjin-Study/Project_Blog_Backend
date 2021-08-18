package com.woonjin.blog.web;

import com.woonjin.blog.application.dto.request.BoardRequest;
import com.woonjin.blog.application.dto.request.ReplyRequest;
import com.woonjin.blog.application.dto.response.ShowBoardResponse;
import com.woonjin.blog.application.dto.response.WriteBoardResponse;
import com.woonjin.blog.application.service.BoardService;
import com.woonjin.blog.domain.entity.Board;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"4. Board Service"})
public class BoardController {

    private final BoardService boardService;

    public BoardController(
        BoardService boardService
    ) {
        this.boardService = boardService;
    }

    @GetMapping("search-board/{words}")
    public void searchBoardWithWords(@PathVariable String words) {
//통합 검색
    }

    @GetMapping("search-board/{contents}")
    public void searchBoardWithContents(@PathVariable String contents) {
//내용으로 검색
    }

    @GetMapping("search-board/{writer}")
    public void searchBoardWithWriter(@PathVariable String writer) {
//작성자로 검색
    }

    @GetMapping("search-board/{title}")
    public void searchBoardWithTitle(@PathVariable String title) {
//제목으로 검색
    }

    @GetMapping("show-board/{id}")
    public ShowBoardResponse showBoard(@PathVariable int id) {
        return this.boardService.showBoard(id);
    }

    @PostMapping("write-board")
    public WriteBoardResponse writeBoard(BoardRequest boardRequest) {
        return this.boardService.writeBoard(boardRequest);
    }

    @DeleteMapping("delete-board/{id}")
    public void deleteBoard(@PathVariable int id) {
        this.boardService.deleteBoard(id);
    }

    @PutMapping("update-board")
    public void updateBoard(BoardRequest boardRequest) {
        this.boardService.updateBoard(boardRequest);
    }

    @PostMapping("write-reply")
    public void writeReply(ReplyRequest replyRequest) {
        this.boardService.writeReply(replyRequest);
    }

    @DeleteMapping("delete-reply/{id}")
    public void deleteReply(@PathVariable int reply_id) {
        this.boardService.deleteReply(reply_id);
    }

    @PutMapping("update-reply/{id}")
    public void updateReply(@PathVariable int reply_id, ReplyRequest replyRequest) {
        this.boardService.updateReply(reply_id, replyRequest);
    }


}
