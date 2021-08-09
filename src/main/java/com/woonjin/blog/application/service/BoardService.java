package com.woonjin.blog.application.service;

import com.woonjin.blog.application.dto.request.BoardRequest;
import com.woonjin.blog.domain.entity.Board;
import com.woonjin.blog.domain.entity.Category;
import com.woonjin.blog.domain.repository.BoardRepository;
import com.woonjin.blog.domain.repository.CategoryRepository;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private final IdentityAppService identityAppService;
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final static Logger Log = Logger.getGlobal();

    public BoardService(
        IdentityAppService identityAppService,
        BoardRepository boardRepository,
        CategoryRepository categoryRepository
    ) {
        this.identityAppService = identityAppService;
        this.boardRepository = boardRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Category> showCategories() {
        return this.categoryRepository.findByUser(this.identityAppService.getAuthenticationUser());
    }

    public Board writeBoard(BoardRequest boardRequest) {
        Board writeBoard = this.boardRepository.save(
            Board.of(
                boardRequest.getTitle(),
                boardRequest.getContent(),
                boardRequest.getCategory()
            )
        );

        Log.info("Write Post Success / " + writeBoard);
        return writeBoard;
    }

    public Board updateBoard(int boardId, BoardRequest boardRequest) {
        Board updateBoard = this.boardRepository.findById(boardId);
        updateBoard.setTitle(boardRequest.getTitle());
        updateBoard.setContent(boardRequest.getContent());
        updateBoard.setCategory(boardRequest.getCategory());

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

//    public void writeReply() {
//        this.boardRepository.save();
//    }
//
//    public void updateReply() {
//        this.boardRepository.save();
//    }
//
//    public void deleteReply() {
//        this.boardRepository.delete();
//    }
}
