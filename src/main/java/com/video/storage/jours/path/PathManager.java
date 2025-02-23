package com.video.storage.jours.path;

import java.nio.file.Path;
import java.util.UUID;

public interface PathManager {

    Path generateOnlyPath(PathType pathType, Extension extension);
    Path generateOnlyPath(PathType pathType);

    Path get(PathType pathType, String... ids);
    Path get(PathType pathType);

    default String generateNewPath() {
        return UUID.randomUUID().toString();
    }

    default String generateNewPath(Extension extension) {
        return extension.appendTo(generateNewPath());
    }

}
