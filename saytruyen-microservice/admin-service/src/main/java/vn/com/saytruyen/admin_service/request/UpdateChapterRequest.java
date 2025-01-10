package vn.com.saytruyen.admin_service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Update chapter request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateChapterRequest {

    private String chapterId;

    private ChapterRequest chapterRequest;
}
