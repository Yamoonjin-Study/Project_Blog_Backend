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
import com.woonjin.blog.domain.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    private final IdentityAppService identityAppService;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final CategoryRepository categoryRepository;
    private final static Logger Log = Logger.getGlobal();

    public BoardService(
        IdentityAppService identityAppService,
        UserRepository userRepository,
        BoardRepository boardRepository,
        ReplyRepository replyRepository,
        CategoryRepository categoryRepository
    ) {
        this.identityAppService = identityAppService;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
        this.replyRepository = replyRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<Board> searchBoardWithWords(String words) {
        User user = this.userRepository.findByNickname(words);

        List<Board> boards = new ArrayList<>();
        List<Board> findByTitleBoards = this.boardRepository.findByTitleContaining(words);
        List<Board> findByContentsBoards = this.boardRepository.findByContentContaining(words);
        List<Board> findByWriterBoards = this.boardRepository.findByUser(user);

        for (int i = 0; i < this.boardRepository.findByTitleContaining(words).size(); i++) {
            boards.add(
                findByTitleBoards.get(i)
            );
            boards.add(
                findByContentsBoards.get(i)
            );
            boards.add(
                findByWriterBoards.get(i)
            );
        }

        return boards;
    }

    @Transactional(readOnly = true)
    public List<Board> searchBoardWithTitle(String title) {
        return this.boardRepository.findByTitleContaining(title);
    }

    @Transactional(readOnly = true)
    public List<Board> searchBoardWithContents(String contents) {
        return this.boardRepository.findByContentContaining(contents);
    }

    @Transactional(readOnly = true)
    public List<Board> searchBoardWithWriter(String writer) {
        User user = this.userRepository.findByNickname(writer);
        return this.boardRepository.findByUser(user);
    }
    @Transactional(readOnly = true)
    public ShowBoardResponse showBoard(int id) {
        return ShowBoardResponse.of(
            this.boardRepository.findById(id),
            this.replyRepository.findByBoard(this.boardRepository.findById(id)),
            "Show Board Success");
    }

    @Transactional
    public List<Category> showCategories() {
        return this.categoryRepository.findByUser(this.identityAppService.getAuthenticationUser());
    }

    @Transactional
    public WriteBoardResponse writeBoard(BoardRequest boardRequest) {
        User user = this.identityAppService.getAuthenticationUser();
        try {
            if (this.categoryRepository.findByName(boardRequest.getCategory()) == null) {
                Board writeBoard = this.boardRepository.save(
                    Board.of(
                        user,
                        boardRequest.getTitle(),
                        boardRequest.getContent(),
                        null
                    )
                );
                Log.info("Write Board Success / " + writeBoard);
                return WriteBoardResponse.of(writeBoard, "Write Board Success");
            } else {
                Board writeBoard = this.boardRepository.save(
                    Board.of(
                        user,
                        boardRequest.getTitle(),
                        boardRequest.getContent(),
                        this.categoryRepository.findByName(boardRequest.getCategory())
                    )
                );
                Log.info("Write Board Success / " + writeBoard);
                return WriteBoardResponse.of(writeBoard, "Write Board Success");
            }
        } catch (Exception e) {
            Log.info("Write Board Fail : " + e.getMessage());
            return WriteBoardResponse.of(Board
                    .of(user, boardRequest.getTitle(), boardRequest.getContent(),
                        this.categoryRepository.findByName(boardRequest.getCategory())),
                "Write Board Fail");
        }
    }

    @Transactional
    public Board updateBoard(BoardRequest boardRequest) {
        int boardId = this.boardRepository.findByTitle(boardRequest.getTitle()).getId();
        Board updateBoard = this.boardRepository.findById(boardId);

        updateBoard.setTitle(boardRequest.getTitle());
        updateBoard.setContent(boardRequest.getContent());
        updateBoard.setCategory(this.categoryRepository.findByName(boardRequest.getCategory()));

        this.boardRepository.save(updateBoard);

        Log.info("Update Post Success / " + updateBoard);
        return updateBoard;
    }

    @Transactional
    public Board deleteBoard(int boardId) {
        Board deleteBoard = this.boardRepository.findById(boardId);
        this.boardRepository.delete(deleteBoard);

        Log.info("Delete Post Success / " + deleteBoard);
        return deleteBoard;
    }

    @Transactional
    public void writeReply(ReplyRequest replyRequest) {
        User user = this.identityAppService.getAuthenticationUser();
        this.replyRepository.save(
            Reply.of(
                this.boardRepository.findById(replyRequest.getTop_board()),
                this.replyRepository.findById(replyRequest.getTop_reply()),
                replyRequest.getContent(),
                user
            )
        );
    }

    @Transactional
    public void updateReply(int reply_id, ReplyRequest replyRequest) {
        Reply updateReply = this.replyRepository.findById(reply_id);

        updateReply.setContent(replyRequest.getContent());

        this.replyRepository.save(updateReply);
    }

    public void deleteReply(int reply_id) {
        this.replyRepository.deleteById(reply_id);
    }

    public void likeBoard(){

    }
}
