package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.domain.entity.Board;
import java.sql.Timestamp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WriteBoardResponse {
    private Board board;

    private String ResponseMessage;

    public static WriteBoardResponse of(Board board, String responseMessage){
        return new WriteBoardResponse(board, responseMessage);
    }
}
