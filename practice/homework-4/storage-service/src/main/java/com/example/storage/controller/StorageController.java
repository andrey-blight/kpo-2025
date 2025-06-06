package com.example.storage.controller;

import com.example.storage.entity.FileEntity;
import com.example.storage.service.FileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/storage")
public class StorageController {

    private final FileService fileService;

    public StorageController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
        try {
            FileEntity savedFile = fileService.uploadFile(file);
            return ResponseEntity.ok(savedFile.getId().toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка: " + e.getMessage());
        }
    }

    @GetMapping("/content/{id}")
    public ResponseEntity<String> getFileContent(@PathVariable int id) {
        Optional<String> data = fileService.getFileContent(id);
        return data.map(s -> ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(s)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}