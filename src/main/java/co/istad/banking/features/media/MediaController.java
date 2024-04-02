package co.istad.banking.features.media;

import co.istad.banking.features.media.dto.MediaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/media")
public class MediaController {

    private final MediaService mediaService;

    @PostMapping("/upload-single-")
    MediaResponse uploadSingle(@RequestPart MultipartFile file) {
        return mediaService.uploadSingle(file, "Images");
    }

}
