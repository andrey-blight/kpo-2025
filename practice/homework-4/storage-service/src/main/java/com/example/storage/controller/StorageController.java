package com.example.storage.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<String> uploadFile(@RequestPart("file") FilePart filePart) {

        return filePart.content()
                .reduce(new byte[0], (acc, dataBuffer) -> {
                    byte[] newBytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(newBytes);
                    byte[] combined = new byte[acc.length + newBytes.length];
                    System.arraycopy(acc, 0, combined, 0, acc.length);
                    System.arraycopy(newBytes, 0, combined, acc.length, newBytes.length);
                    return combined;
                })
                .handle((bytes, sink) -> {
                    try {
                        MessageDigest digest = MessageDigest.getInstance("SHA-256");
                        byte[] hashBytes = digest.digest(bytes);
                        StringBuilder sb = new StringBuilder();
                        for (byte b : hashBytes) {
                            sb.append(String.format("%02x", b));
                        }
                        sink.next("SHA-256 хеш файла: " + sb);
                    } catch (NoSuchAlgorithmException e) {
                        sink.error(new RuntimeException("Ошибка при вычислении хеша", e));
                    }
                });
    }
}