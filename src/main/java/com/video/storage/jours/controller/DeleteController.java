package com.video.storage.jours.controller;

import com.video.storage.jours.path.PathType;
import com.video.storage.jours.service.DeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DeleteController {

    private final DeleteService deleteService;

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("pathType") PathType pathType, @RequestParam("storeKey") String storeKey) {
        log.info("DELETE : {}, Store Key : {}", pathType, storeKey);
        deleteService.delete(pathType, storeKey);
        return ResponseEntity.ok("success");
    }
}
