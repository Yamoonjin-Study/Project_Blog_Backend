package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.domain.entity.Board;
import com.woonjin.blog.domain.entity.Reply;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShowBoardResponse {
    private Board board;

    private List<Reply> reply;

    private String ResponseMessage;

    public static ShowBoardResponse of(Board board, List<Reply> reply, String responseMessage){
        return new ShowBoardResponse(board, reply, responseMessage);
    }
}
