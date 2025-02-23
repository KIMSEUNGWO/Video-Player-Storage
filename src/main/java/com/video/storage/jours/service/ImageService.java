package com.video.storage.jours.service;

import com.video.storage.jours.component.ImageConverter;
import com.video.storage.jours.path.PathManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

import static com.video.storage.jours.path.PathType.*;


@Service
@RequiredArgsConstructor
public class ImageService {

    private final PathManager pathManager;
    private final ImageConverter imageConverter;

    public String uploadThumbnail(MultipartFile thumbnail) throws IOException {

        Path thumbnailPath = pathManager.generateOnlyPath(() ->
            pathManager.get(THUMBNAIL, pathManager.generateNewPath(".webp"))
        );

        imageConverter.convertToWebP(thumbnail, thumbnailPath,100);
        return thumbnailPath.getFileName().toString();
    }

}
