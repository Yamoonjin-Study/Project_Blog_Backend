package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.domain.entity.Blog;
import com.woonjin.blog.domain.entity.User;
import java.sql.Timestamp;
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
public class ShowVisitorsResponse {

    private String responseMessage;

    private String blogname;

    private List<VisitorInfo> visitorInfo;


    public static ShowVisitorsResponse of(String responseMessage, String blogname, List<VisitorInfo> visitorInfo) {
        return new ShowVisitorsResponse(responseMessage, blogname, visitorInfo);
    }
}
