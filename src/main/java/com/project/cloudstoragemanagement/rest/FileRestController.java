package com.project.cloudstoragemanagement.rest;

import com.project.cloudstoragemanagement.entity.File;
import com.project.cloudstoragemanagement.entity.NormalUser;
import com.project.cloudstoragemanagement.services.IFileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("fileapi")
@RestController
@CrossOrigin("http://localhost:3000")
public class FileRestController {
    private IFileServices iFileServices;

    @Autowired
    public FileRestController(IFileServices iFileServices) {
        this.iFileServices = iFileServices;
    }

    @GetMapping("files")
    List<File> findAllFile() {
        return iFileServices.findAllFile();
    }
    @GetMapping("files/{requestedId}")
    File findByFileId(@PathVariable int requestedId){
        File file = iFileServices.findByFileId(requestedId);
        if(file==null){
            throw new RuntimeException("Requested file id is not found");
        }
        else {
            return file;
        }
    }
    @PostMapping("/files")
    File addFile(@RequestBody File file){
        File savedFile = iFileServices.saveFile(file);
        return savedFile;
    }

    @PutMapping("/files")
    File updateFile(@RequestBody File file)
    {
        File updateFile = iFileServices.saveFile(file);
        return updateFile;
    }

    @DeleteMapping("files/{requestedId}")
    String deleteByFileId(@PathVariable int requestedId){
        File searchFile = iFileServices.findByFileId(requestedId);
        if(searchFile == null){
            throw new RuntimeException("Requested file id is not found");
        }
        else {
            iFileServices.deleteByFileId(requestedId);
        }
        return "Delete id "+requestedId+" from the database";
    }

    @GetMapping("files/users/{userId}")
    List<File> findAllUserFiles(@PathVariable int userId) {
        List<File> files = iFileServices.findByUserId(userId);
        if(files == null){
            throw new RuntimeException("There is no files for this user.");
        }
        else {
            return files;
        }
    }


}
