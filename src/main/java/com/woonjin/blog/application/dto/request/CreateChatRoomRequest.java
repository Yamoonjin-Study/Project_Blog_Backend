package com.woonjin.blog.application.dto.request;

import java.util.List;
import lombok.Getter;

@Getter
public class CreateChatRoomRequest {

    private String chatRoomName;

    private List<String> chatRoomBloggerName;
}
