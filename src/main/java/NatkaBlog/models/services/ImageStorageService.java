package NatkaBlog.models.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageStorageService {
    public String store(MultipartFile file) throws IOException;

}
