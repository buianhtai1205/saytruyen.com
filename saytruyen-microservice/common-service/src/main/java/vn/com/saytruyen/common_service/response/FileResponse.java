package vn.com.saytruyen.common_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type File response.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {

    private String fileName;

    private String fileUrl;

    private String fileType;

    private long size;
}
