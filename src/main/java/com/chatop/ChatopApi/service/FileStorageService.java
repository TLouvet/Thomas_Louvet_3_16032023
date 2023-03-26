package com.chatop.ChatopApi.service;

import com.chatop.ChatopApi.exceptions.FileNameException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileStorageService {

    private final Path root = Paths.get("uploads").toAbsolutePath().normalize();

    public void init(){
        try {
            Files.createDirectories(root);
        } catch (IOException e){
            throw new RuntimeException("Impossible to create uploads directory");
        }
    }

    public String save(MultipartFile file){
        try {
            String recordedFilename = this.formatFileName(file);
            Files.copy(file.getInputStream(), this.root.resolve(Objects.requireNonNull(recordedFilename)));
            return recordedFilename;
        } catch (FileAlreadyExistsException e){
            throw new RuntimeException("A file of that name already exists.");
        } catch (Exception e ){
            throw new RuntimeException(e.getMessage());
        }
    }

    private String formatFileName(MultipartFile file) throws FileNameException {
        long currentDate = System.currentTimeMillis();
        String filename = file.getOriginalFilename();
        if (filename == null || filename.isEmpty()){
            throw new FileNameException("Filename can't be null or empty");
        }

        int indexOfExtension = filename.lastIndexOf(".");
        return filename.substring(0, indexOfExtension) + currentDate + filename.substring(indexOfExtension);
    }
}
