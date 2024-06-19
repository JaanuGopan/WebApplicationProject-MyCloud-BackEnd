package com.project.cloudstoragemanagement.services;

import com.project.cloudstoragemanagement.entity.File;

import java.util.List;

public interface IFileServices {
    List<File> findAllFile();
    File findByFileId(int id);
    File saveFile(File file);
    void deleteByFileId(int id);
    List<File> findByUserId(int userId);
}
