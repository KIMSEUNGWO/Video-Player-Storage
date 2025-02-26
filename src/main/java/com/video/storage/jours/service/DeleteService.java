package com.video.storage.jours.service;

import com.jours.easy_ffmpeg.DirectoryManager;
import com.video.storage.jours.path.PathManager;
import com.video.storage.jours.path.PathType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteService {

    private final DirectoryManager directoryManager;
    private final PathManager pathManager;

    public void delete(PathType pathType, String storeKey) {
        if (pathType == null || storeKey == null || storeKey.isEmpty()) return;
        directoryManager.deleteIfExists(pathManager.get(pathType, storeKey));
    }
}
