package com.woonjin.blog.application.service;

import com.woonjin.blog.application.dto.request.BoardRequest;
import com.woonjin.blog.application.dto.request.ReplyRequest;
import com.woonjin.blog.application.dto.response.ShowBoardResponse;
import com.woonjin.blog.application.dto.response.WriteBoardResponse;
import com.woonjin.blog.domain.entity.Board;
import com.woonjin.blog.domain.entity.Category;
import com.woonjin.blog.domain.entity.Reply;
import com.woonjin.blog.domain.entity.User;
import com.woonjin.blog.domain.repository.BoardRepository;
import com.woonjin.blog.domain.repository.CategoryRepository;
import com.woonjin.blog.domain.repository.ReplyRepository;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private final IdentityAppService identityAppService;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final CategoryRepository categoryRepository;
    private final static Logger Log = Logger.getGlobal();

    public BoardService(
        IdentityAppService identityAppService,
        BoardRepository boardRepository,
        ReplyRepository replyRepository,
        CategoryRepository categoryRepository
    ) {
        this.identityAppService = identityAppService;
        this.boardRepository = boardRepository;
        this.replyRepository = replyRepository;
        this.categoryRepository = categoryRepository;
    }

    public ShowBoardResponse showBoard(int id){
        return ShowBoardResponse.of(
            this.boardRepository.findById(id),
            this.replyRepository.findByBoard(this.boardRepository.findById(id)),
            "Show Board Success");
    }

    public List<Category> showCategories() {
        return this.categoryRepository.findByUser(this.identityAppService.getAuthenticationUser());
    }

    public WriteBoardResponse writeBoard(BoardRequest boardRequest) {
        User user = this.identityAppService.getAuthenticationUser();
        List<String> category = this.categoryRepository.findNameByUser(user);

        if(category.contains(boardRequest.getCategory())){

            Board writeBoard = this.boardRepository.save(
                Board.of(
                    boardRequest.getTitle(),
                    boardRequest.getContent(),
                    this.categoryRepository.findByName(boardRequest.getCategory())
                )
            );

            Log.info("Write Board Success / " + writeBoard);
            return WriteBoardResponse.of(writeBoard, "Write Board Success");
        }else {
            Log.info("Write Board Fail");
            return WriteBoardResponse.of(null, "Write Board Success");
        }
    }

    public Board updateBoard(BoardRequest boardRequest) {
        int boardId = this.boardRepository.findByTitle(boardRequest.getTitle()).getId();
        Board updateBoard = this.boardRepository.findById(boardId);

        updateBoard.setTitle(boardRequest.getTitle());
        updateBoard.setContent(boardRequest.getContent());
        updateBoard.setCategory(this.categoryRepository.findByName(boardRequest.getCategory()));

        this.boardRepository.save(updateBoard);

        Log.info("Update Post Success / "+updateBoard);
        return updateBoard;
    }

    public Board deleteBoard(int boardId) {
        Board deleteBoard = this.boardRepository.findById(boardId);
        this.boardRepository.delete(deleteBoard);

        Log.info("Delete Post Success / "+deleteBoard);
        return deleteBoard;
    }

    public void writeReply(ReplyRequest replyRequest) {
        this.replyRepository.save(
            Reply.of(
                replyRequest.getTop_board(),
                replyRequest.getTop_reply(),
                replyRequest.getContent(),
                replyRequest.getWriter()
            )
        );
    }

    public void updateReply(int reply_id, ReplyRequest replyRequest) {
        Reply updateReply = this.replyRepository.findById(reply_id);

        updateReply.setContent(replyRequest.getContent());

        this.replyRepository.save(updateReply);
    }

    public void deleteReply(int reply_id) {
        this.replyRepository.deleteById(reply_id);
    }
}
