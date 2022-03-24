package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.domain.entity.Archive;
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
public class ArchiveResponse {

    private String responseMessage;

    private List<Archive> archive;

    public static ArchiveResponse of(String responseMessage, List<Archive> archive) {
        return new ArchiveResponse(responseMessage, archive);
    }
}
