package com.video.storage.jours.controller;

import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;
import java.nio.file.Path;

public interface DefaultResourceMethod {

    default UrlResource resource(Path path) throws MalformedURLException {
        return new UrlResource(path.toUri());
    }
    default UrlResource resource(String path) throws MalformedURLException {
        return new UrlResource(String.format("file:%s", path));
    }
}
