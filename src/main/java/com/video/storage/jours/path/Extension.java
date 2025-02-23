package com.video.storage.jours.path;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Extension {

    WEBP(".webp");

    private final String extension;

    public String appendTo(String fileName) {
        return String.format("%s%s", fileName, this.extension);
    }

    @Override
    public String toString() {
        return extension;
    }
}
