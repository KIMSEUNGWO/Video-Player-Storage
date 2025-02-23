package com.video.storage.jours.service;

import com.video.storage.jours.path.PathManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.video.storage.jours.path.PathType.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoService {

    private final PathManager pathManager;

    public String uploadOriginalVideo(MultipartFile originalVideo) throws IOException {
        Path originalVideoFilePath = pathManager.generateOnlyPath(() ->
            pathManager.get(ORIGINAL_VIDEO, pathManager.generateNewPath())
        );

        // 필요한 디렉토리 생성
        Files.createDirectories(originalVideoFilePath.getParent());

        // 파일 저장
        Files.copy(originalVideo.getInputStream(), originalVideoFilePath);
        return originalVideoFilePath.getFileName().toString();
    }

}
