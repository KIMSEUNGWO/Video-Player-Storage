package com.video.storage.jours.path;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class LocalPathManager implements PathManager {

    @Value("${directory.video}")
    private String videoDirectory;
    @Value("${directory.original}")
    private String originalVideoDirectory;
    @Value("${directory.image}")
    private String imageDirectory;


    @Override
    public Path get(PathType pathType, String... ids) {
        return Paths.get(getPath(pathType), ids);
    }

    @Override
    public Path get(PathType pathType) {
        return Paths.get(getPath(pathType));
    }

    private String getPath(PathType pathType) {
        return switch (pathType) {
            case VIDEO -> videoDirectory;
            case ORIGINAL_VIDEO -> originalVideoDirectory;
            case THUMBNAIL -> imageDirectory;
        };
    }

    @Override
    public Path generateOnlyPath(PathType pathType, Extension extension) {
        return generate(pathType, generateNewPath(extension));
    }

    @Override
    public Path generateOnlyPath(PathType pathType) {
        return generate(pathType, generateNewPath());
    }

    private Path generate(PathType pathType, String resolve) {
        Path path = get(pathType);
        Path newPath = null;
        do {
            newPath = path.resolve(resolve);
        } while (Files.exists(newPath));
        return newPath;
    }


}
