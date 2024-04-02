package co.istad.banking.features.media;

import co.istad.banking.features.media.dto.MediaResponse;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {

    MediaResponse uploadSingle(MultipartFile file, String folderName);
}
