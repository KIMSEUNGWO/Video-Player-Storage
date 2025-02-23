package com.video.storage.jours.controller;

import com.video.storage.jours.dto.StorageResponse;
import com.video.storage.jours.path.PathManager;
import com.video.storage.jours.service.ImageService;
import com.video.storage.jours.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/upload")
public class UploadController {

    private final ImageService imageService;
    private final VideoService videoService;
    private final PathManager pathManager;

    @PostMapping("/thumbnail")
    public ResponseEntity<StorageResponse> uploadThumbnail(@RequestParam("file") MultipartFile image) throws IOException {
        System.out.println("image.getOriginalFilename() = " + image.getOriginalFilename());
        String storeKey = imageService.uploadThumbnail(image);
        return ResponseEntity.ok(new StorageResponse(storeKey, "Thumbnail uploaded successfully"));
    }

    @PostMapping("/original")
    public ResponseEntity<StorageResponse> uploadOriginalVideo(@RequestParam("file") MultipartFile originalVideo) throws IOException {
        System.out.println("originalVideo.getOriginalFilename() = " + originalVideo.getOriginalFilename());
        String storeKey = videoService.uploadOriginalVideo(originalVideo);
        return ResponseEntity.ok(new StorageResponse(storeKey, "Original video uploaded successfully"));
    }

    @PostMapping("/hls")
    public ResponseEntity<Void> uploadHls(@RequestParam("file") MultipartFile zipFile) throws IOException {
        System.out.println("UploadController.uploadHls");
        // ZIP 파일 압축 해제 및 저장
        unzipAndStore(zipFile);
        return ResponseEntity.ok().build();
    }

    private void unzipAndStore(MultipartFile zipFile) throws IOException {
        Path storageDir = Paths.get("/Users/tmd8635/Desktop/directory/video");

        try (ZipInputStream zis = new ZipInputStream(zipFile.getInputStream())) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                Path filePath = storageDir.resolve(entry.getName());

                // 디렉토리 생성
                if (entry.isDirectory()) {
                    Files.createDirectories(filePath);
                } else {
                    Files.createDirectories(filePath.getParent());
                    Files.copy(zis, filePath, StandardCopyOption.REPLACE_EXISTING);
                }

                zis.closeEntry();
            }
        }
    }
}
