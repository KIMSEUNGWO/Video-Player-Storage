package com.video.storage.jours.service;

import com.video.storage.jours.path.PathManager;
import com.video.storage.jours.path.PathType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
@RequiredArgsConstructor
public class ZipService {

    private final PathManager pathManager;

    public void unzip(PathType pathType, MultipartFile zipFile) throws IOException {
        Path basePath = pathManager.get(pathType);

        try (ZipInputStream zis = new ZipInputStream(zipFile.getInputStream())) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                try {
                    extractEntry(zis, entry, basePath);
                } finally {
                    zis.closeEntry();
                }
            }
        }
    }

    private void extractEntry(ZipInputStream zis, ZipEntry entry, Path basePath) throws IOException {
        Path filePath = basePath.resolve(entry.getName());

        if (entry.isDirectory()) {
            Files.createDirectories(filePath);
            return;
        }

        Files.createDirectories(filePath.getParent());
        Files.copy(zis, filePath, StandardCopyOption.REPLACE_EXISTING);
    }
}
