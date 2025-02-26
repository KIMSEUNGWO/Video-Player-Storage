package com.video.storage.jours.service;

import com.jours.easy_ffmpeg.FFmpegConverter;
import com.video.storage.jours.path.Extension;
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
    private final FFmpegConverter fFmpegConverter;

    public String uploadThumbnail(MultipartFile thumbnail) throws IOException {

        Path thumbnailPath = pathManager.generateOnlyPath(THUMBNAIL, Extension.WEBP);

        fFmpegConverter.convertToWebP(thumbnail, thumbnailPath, 100);
        return thumbnailPath.getFileName().toString();
    }

}
