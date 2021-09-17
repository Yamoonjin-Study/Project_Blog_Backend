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
public class SearchBlogResponse {

    private String responseMessage;

    private List<String> results;

    public static SearchBlogResponse of(String responseMessage, List<String> results) {
        return new SearchBlogResponse(responseMessage, results);
    }
}
