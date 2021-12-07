package com.woonjin.blog.application.service;

import com.woonjin.blog.application.dto.request.BoardRequest;
import com.woonjin.blog.application.dto.request.ReplyRequest;
import com.woonjin.blog.application.dto.response.ShowBoardResponse;
import com.woonjin.blog.application.dto.response.WriteBoardResponse;
import com.woonjin.blog.domain.entity.Board;
import com.woonjin.blog.domain.entity.Category;
import com.woonjin.blog.domain.entity.Like;
import com.woonjin.blog.domain.entity.Reply;
import com.woonjin.blog.domain.entity.User;
import com.woonjin.blog.domain.repository.BoardRepository;
import com.woonjin.blog.domain.repository.CategoryRepository;
import com.woonjin.blog.domain.repository.LikeRepository;
import com.woonjin.blog.domain.repository.ReplyRepository;
import com.woonjin.blog.domain.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    private final IdentityAppService identityAppService;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final CategoryRepository categoryRepository;
    private final LikeRepository likeRepository;
    private final static Logger Log = Logger.getGlobal();

    public BoardService(
        IdentityAppService identityAppService,
        UserRepository userRepository,
        BoardRepository boardRepository,
        ReplyRepository replyRepository,
        CategoryRepository categoryRepository,
        LikeRepository likeRepository
    ) {
        this.identityAppService = identityAppService;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
        this.replyRepository = replyRepository;
        this.categoryRepository = categoryRepository;
        this.likeRepository = likeRepository;
    }

    @Transactional(readOnly = true)
    public List<Board> searchBoardWithWords(String words) {
        User user = this.userRepository.findByNickname(words);

        List<Board> boards = new ArrayList<>();
        int x = 0;
        int y = 0;
        int z = 0;
        List<Board> boardsWithWriter = this.boardRepository.findByUser(user);
        List<Board> boardsWithTitle = this.searchBoardWithTitle(words);
        List<Board> boardsWithContent = this.searchBoardWithContents(words);

        while (true) {
            if(x == boardsWithWriter.size()){
                break;
            }
            boards.add(boardsWithWriter.get(x));
            x++;
        }
        while (true) {
            if(y == boardsWithTitle.size()){
                break;
            }
            boards.add(boardsWithTitle.get(y));
            y++;
        }
        while (true) {
            if(z == boardsWithContent.size()){
                break;
            }
            boards.add(boardsWithContent.get(z));
            z++;
        }
        return boards;
    }

    @Transactional(readOnly = true)
    public List<Board> searchBoardWithTitle(String title) {
        return this.boardRepository.findByTitleContaining(title);
    }

    @Transactional(readOnly = true)
    public List<Board> searchBoardWithContents(String contents) {
        List<Board> boardListWithContents = new ArrayList<>();
        List<Board> boardList = this.boardRepository.findAll();

        for (int x = 0; x < boardList.size(); x++) {
            if (boardList.get(x).getContent().contains(contents)) {
                boardListWithContents.add(boardList.get(x));
            }
        }
        return boardListWithContents;
    }

    @Transactional(readOnly = true)
    public List<Board> searchBoardWithWriter(String writer) {
        User user = this.userRepository.findByNickname(writer);
        return this.boardRepository.findByUser(user);
    }

    @Transactional(readOnly = true)
    public List<Board> showBoardList() {
        return this.boardRepository.findByUser(this.identityAppService.getAuthenticationUser());
    }

    @Transactional(readOnly = true)
    public ShowBoardResponse showBoard(int id) {
        return ShowBoardResponse.of(
            this.boardRepository.findById(id),
            this.likeRepository.findByBoard(this.boardRepository.findById(id)),
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
        Reply reply = this.replyRepository.save(
            Reply.of(
                this.boardRepository.findById(replyRequest.getTop_board_id()),
                this.replyRepository.findById(replyRequest.getTop_reply_id()),
                replyRequest.getContent(),
                user
            )
        );

        Log.info("Write Reply Success / " + reply);
    }

    @Transactional
    public void updateReply(int reply_id, ReplyRequest replyRequest) {
        Reply updateReply = this.replyRepository.findById(reply_id);

        updateReply.setContent(replyRequest.getContent());

        this.replyRepository.save(updateReply);

        Log.info("Update Reply Success / " + updateReply);
    }

    @Transactional
    public void deleteReply(int reply_id) {
        this.replyRepository.deleteById(reply_id);

        Log.info("Delete Reply Success");
    }

    @Transactional
    public void likeBoard(int board_id) {
        User user = this.identityAppService.getAuthenticationUser();
        Board board = this.boardRepository.findById(board_id);
        if (this.likeRepository.findByBoardAndUser(board, user) == null) {
            Like like = this.likeRepository.save(
                Like.of(
                    board,
                    null,
                    user
                )
            );

            Log.info("Like Board Success / " + like);
        } else {
            Log.warning("Like Board Fail / reason : you already liked this board.");
        }
    }

    @Transactional
    public void dislikeBoard(int board_id) {
        User user = this.identityAppService.getAuthenticationUser();
        Board board = this.boardRepository.findById(board_id);
        Like like = this.likeRepository.findByBoardAndUser(board, user);
        if (like != null) {
            this.likeRepository.delete(like);
            Log.info("Dislike Board Success / " + like);
        } else {
            Log.warning("Dislike Board Fail");
        }
    }

    @Transactional
    public void likeReply(int reply_id) {
        User user = this.identityAppService.getAuthenticationUser();
        Reply reply = this.replyRepository.findById(reply_id);
        if (this.likeRepository.findByReplyAndUser(reply, user) == null) {
            Like like = this.likeRepository.save(
                Like.of(
                    null,
                    reply,
                    user
                )
            );

            Log.info("Like Board Success / " + like);
        } else {
            Log.warning("Like Board Fail / reason : you already liked this board.");
        }
    }

    @Transactional
    public void dislikeReply(int reply_id) {
        User user = this.identityAppService.getAuthenticationUser();
        Reply reply = this.replyRepository.findById(reply_id);
        Like like = this.likeRepository.findByReplyAndUser(reply, user);
        if (like != null) {
            this.likeRepository.delete(like);
            Log.info("Dislike Reply Success / " + like);
        } else {
            Log.warning("Dislike Reply Fail");
        }
    }
}
