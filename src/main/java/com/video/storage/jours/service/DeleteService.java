package com.video.storage.jours.service;

import com.video.storage.jours.path.DirectoryManager;
import com.video.storage.jours.path.PathManager;
import com.video.storage.jours.path.PathType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteService {

    private final DirectoryManager directoryManager;

    public void delete(PathType pathType, String storeKey) {
        if (pathType == null || storeKey == null || storeKey.isEmpty()) return;
        directoryManager.delete(pathType, storeKey);
    }
}
