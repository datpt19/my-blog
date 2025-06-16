package unicorns.backend.service;

import unicorns.backend.dto.request.UploadFileRequest;

import java.io.IOException;

public interface FileService {
    void upload(UploadFileRequest uploadFileRequest) throws IOException;
}
