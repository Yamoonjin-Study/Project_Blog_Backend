package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.domain.entity.Archive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateArchiveResponse {

    private String responseMessage;

    private Archive archive;

    public static CreateArchiveResponse of(String responseMessage, Archive archive) {
        return new CreateArchiveResponse(responseMessage, archive);
    }
}
