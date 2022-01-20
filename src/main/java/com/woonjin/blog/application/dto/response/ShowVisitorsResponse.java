package com.woonjin.blog.application.dto.response;

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

    private String blogName;

    private List<VisitorInfo> visitorInfo;


    public static ShowVisitorsResponse of(String responseMessage, String blogName, List<VisitorInfo> visitorInfo) {
        return new ShowVisitorsResponse(responseMessage, blogName, visitorInfo);
    }
}
