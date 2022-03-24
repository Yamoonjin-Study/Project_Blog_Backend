package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.domain.entity.User;
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
public class SendAlarmResponse {

    private String alarmMessage;

    private String url;

    private User sender;

    private User receiver;

    private Timestamp alarmDate;

    public static SendAlarmResponse of(
        String alarmMessage,
        String url,
        User sender,
        User receiver,
        Timestamp alarmDate
    ) {
        return new SendAlarmResponse(alarmMessage, url, sender, receiver, alarmDate);
    }
}
