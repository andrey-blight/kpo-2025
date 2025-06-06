package com.example.storage.service;

import com.example.storage.entity.FileEntity;
import com.example.storage.repository.FileRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.security.MessageDigest;
import java.util.Optional;

@Service
public class FileService {

    private final FileRepository fileRepository;
    @Setter
    private Path storageDir = Paths.get("file-storage");

    public FileService(FileRepository fileRepository) throws IOException {
        this.fileRepository = fileRepository;
        Files.createDirectories(storageDir);
    }

    public FileEntity uploadFile(MultipartFile multipartFile) throws Exception {
        byte[] bytes = multipartFile.getBytes();
        String hash = computeHash(bytes);

        Optional<FileEntity> existing = fileRepository.findByHash(hash);
        if (existing.isPresent()) {
            return existing.get();
        }


        String fileName = multipartFile.getOriginalFilename();
        assert fileName != null;

        // Сохраняем файл в базу данных пока без правильного указания location
        FileEntity fileEntity = new FileEntity();
        fileEntity.setHash(hash);
        fileEntity.setLocation("None");
        fileEntity.setName(fileName);
        fileEntity = fileRepository.save(fileEntity);

        // Сохраняем файл в виде {id}_{filename} и обновляем в базе данных
        Path filePath = storageDir.resolve(fileEntity.getId().toString() + "_" + fileName);
        fileEntity.setLocation(filePath.toAbsolutePath().toString());
        Files.write(filePath, bytes, StandardOpenOption.CREATE);

        return fileRepository.save(fileEntity);
    }

    public Optional<String> getFileContent(int id) {
        try {
            Optional<FileEntity> fileOptional = fileRepository.findById((long) id);
            if (fileOptional.isEmpty()) {
                return Optional.empty();
            }

            FileEntity file = fileOptional.get();
            Path path = Paths.get(file.getLocation());

            if (!Files.exists(path)) {
                return Optional.empty();
            }

            String content = Files.readString(path);
            return Optional.of(content);

        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private String computeHash(byte[] bytes) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(bytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}