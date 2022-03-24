package com.woonjin.blog.application.dto.request;

import com.woonjin.blog.domain.entity.Alarm.MessageType;
import lombok.Getter;

@Getter
public class SendAlarmRequest {

    private String message;

    private String url;

    private int sender_id;

    private int receiver_id;

    private MessageType messageType;
}
