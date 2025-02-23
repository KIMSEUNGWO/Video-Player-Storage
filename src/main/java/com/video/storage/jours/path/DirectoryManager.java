package com.video.storage.jours.path;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;

@Slf4j
@Component
@RequiredArgsConstructor
public class DirectoryManager {

    private final PathManager pathManager;
    private final SimpleFileVisitor<Path> deleteVisitor;

    public void delete(PathType pathType, String id) {
        if (pathType != null && id != null && !id.isBlank()) {
            deleteIfExists(pathManager.get(pathType, id));
        }
    }

    public void deleteOnlyFile(PathType pathType, String id) {
        if (id != null) {
            deleteOnlyFileIfExists(pathManager.get(pathType, id));
        }
    }

    private void deleteOnlyFileIfExists(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            log.error("Failed to delete: {}", path, e);
        }
    }

    private void deleteIfExists(Path path) {
        try {
            if (Files.isDirectory(path)) {
                Files.walkFileTree(path, deleteVisitor);
            } else {
                Files.deleteIfExists(path);
            }
        } catch (IOException e) {
            log.error("Failed to delete: {}", path, e);
        }
    }

}
