package com.woonjin.blog.web;

import com.woonjin.blog.application.dto.request.BoardRequest;
import com.woonjin.blog.application.dto.request.ReplyRequest;
import com.woonjin.blog.application.dto.request.UpdateReplyRequest;
import com.woonjin.blog.application.dto.response.LikeBoardResponse;
import com.woonjin.blog.application.dto.response.LikeReplyResponse;
import com.woonjin.blog.application.dto.response.ShowBoardResponse;
import com.woonjin.blog.application.dto.response.ShowLikeReplyResponse;
import com.woonjin.blog.application.dto.response.WriteBoardResponse;
import com.woonjin.blog.application.dto.response.WriteReplyResponse;
import com.woonjin.blog.application.service.BoardService;
import com.woonjin.blog.domain.entity.Board;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/search-board/{words}")
    public List<Board> searchBoardWithWords(@PathVariable String words) {
//통합 검색
        return this.boardService.searchBoardWithWords(words);
    }

    @GetMapping("/search-board/contents/{contents}")
    public List<Board> searchBoardWithContents(@PathVariable String contents) {
//내용으로 검색
        return this.boardService.searchBoardWithContents(contents);
    }

    @GetMapping("/search-board/writer/{writer}")
    public List<Board> searchBoardWithWriter(@PathVariable String writer) {
//작성자로 검색
        return this.boardService.searchBoardWithWriter(writer);
    }

    @GetMapping("/search-board/title/{title}")
    public List<Board> searchBoardWithTitle(@PathVariable String title) {
//제목으로 검색
        return this.boardService.searchBoardWithTitle(title);
    }

    @GetMapping("/board-list/{blog_name}")
    public List<Board> showBoardList(@PathVariable String blog_name) {
        return this.boardService.showBoardList(blog_name);
    }

    @GetMapping("/show-board/{id}")
    public ShowBoardResponse showBoard(@PathVariable int id) {
        return this.boardService.showBoard(id);
    }

    @PostMapping("/write-board")
    public WriteBoardResponse writeBoard(@RequestBody BoardRequest boardRequest) {
        return this.boardService.writeBoard(boardRequest);
    }

    @DeleteMapping("/delete-board/{id}")
    public void deleteBoard(@PathVariable int id) {
        this.boardService.deleteBoard(id);
    }

    @PutMapping("/update-board/{id}")
    public void updateBoard(@RequestBody BoardRequest boardRequest, @PathVariable int id) {
        this.boardService.updateBoard(boardRequest, id);
    }

    @PostMapping("/write-reply")
    public WriteReplyResponse writeReply(@RequestBody ReplyRequest replyRequest) {
        return this.boardService.writeReply(replyRequest);
    }

    @DeleteMapping("/delete-reply/{reply_id}")
    public void deleteReply(@PathVariable int reply_id) {
        this.boardService.deleteReply(reply_id);
    }

    @PutMapping("/update-reply/{reply_id}")
    public void updateReply(@PathVariable int reply_id,
        @RequestBody UpdateReplyRequest updateReplyRequest) {
        this.boardService.updateReply(reply_id, updateReplyRequest);
    }

    @GetMapping("/like-board/{board_id}")
    public LikeBoardResponse likeBoard(@PathVariable int board_id) {
        return this.boardService.likeBoard(board_id);
    }

    @DeleteMapping("/dislike-board/{board_id}")
    public void dislikeBoard(@PathVariable int board_id) {
        this.boardService.dislikeBoard(board_id);
    }

    @GetMapping("/show-like-reply/{reply_id}")
    public ShowLikeReplyResponse showLikeReply(@PathVariable int reply_id) {
        return this.boardService.showLikeReply(reply_id);
    }

    @GetMapping("/like-reply/{reply_id}")
    public LikeReplyResponse likeReply(@PathVariable int reply_id) {
        return this.boardService.likeReply(reply_id);
    }

    @DeleteMapping("/dislike-reply/{reply_id}")
    public void dislikeReply(@PathVariable int reply_id) {
        this.boardService.dislikeReply(reply_id);
    }

}
