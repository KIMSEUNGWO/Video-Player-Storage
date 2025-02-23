package com.video.storage.jours.controller;

import com.video.storage.jours.service.ZipService;
import com.video.storage.jours.dto.StorageResponse;
import com.video.storage.jours.path.PathType;
import com.video.storage.jours.service.ImageService;
import com.video.storage.jours.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/upload")
public class UploadController {

    private final ImageService imageService;
    private final VideoService videoService;
    private final ZipService zipService;

    @PostMapping("/thumbnail")
    public ResponseEntity<StorageResponse> uploadThumbnail(@RequestParam("file") MultipartFile image) throws IOException {
        log.info("UPLOAD Thumbnail : {} ", image.getOriginalFilename());
        String storeKey = imageService.uploadThumbnail(image);
        return ResponseEntity.ok(new StorageResponse(storeKey, "Thumbnail uploaded successfully"));
    }

    @PostMapping("/original")
    public ResponseEntity<StorageResponse> uploadOriginalVideo(@RequestParam("file") MultipartFile originalVideo) throws IOException {
        log.info("UPLOAD Original Video : {} ", originalVideo.getOriginalFilename());
        String storeKey = videoService.uploadOriginalVideo(originalVideo);
        return ResponseEntity.ok(new StorageResponse(storeKey, "Original video uploaded successfully"));
    }

    @PostMapping("/hls")
    public ResponseEntity<Void> uploadHls(@RequestParam("file") MultipartFile zipFile) throws IOException {
        log.info("UPLOAD HLS VIDEO : {} ", zipFile.getOriginalFilename());
        zipService.unzip(PathType.VIDEO, zipFile);
        return ResponseEntity.ok().build();
    }

}
