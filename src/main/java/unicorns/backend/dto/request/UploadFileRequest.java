package unicorns.backend.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadFileRequest {
    MultipartFile file;
    Long userId;
}
