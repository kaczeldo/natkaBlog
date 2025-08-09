package NatkaBlog.models.services;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageStorageServiceImpl implements ImageStorageService{
    private final String uploadDir = System.getProperty("user.dir") + "/uploads/images/";

    @Override
    public String store(MultipartFile file) throws IOException {
        String originalFilename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path destination = Paths.get(uploadDir).resolve(originalFilename).normalize();
        Files.createDirectories(destination.getParent());
        //save original size file
        file.transferTo(destination.toFile());

        //create thumbnail
        String thumbFilename = "thumb_" + originalFilename;
        Path thumbDestination = Paths.get(uploadDir).resolve(thumbFilename).normalize();

        Thumbnails.of(destination.toFile())
                .size(300, 300)
                .toFile(thumbDestination.toFile());

        return "/uploads/images/" + originalFilename;
    }
}
