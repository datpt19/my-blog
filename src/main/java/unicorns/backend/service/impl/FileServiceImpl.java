package unicorns.backend.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import unicorns.backend.service.FileService;
import unicorns.backend.dto.request.UploadFileRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class FileServiceImpl implements FileService {

    @Override
    public void upload(UploadFileRequest uploadFileRequest) throws IOException {
        MultipartFile file = uploadFileRequest.getFile();
        String uploadDir = "";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filepath = Paths.get(uploadDir, filename);
        Files.write(filepath, file.getBytes());
//        String fileUrl = "http://localhost:8080/" + uploadDir + filename;
        String fileUrl = "https://i1-vnexpress.vnecdn.net/2025/06/12/OPEN-PC-1749726315-8703-1749726413.png?w=0&h=0&q=100&dpr=1&fit=crop&s=EdGUC04-LVwjGwm67Ai2TQ";
    }
}
