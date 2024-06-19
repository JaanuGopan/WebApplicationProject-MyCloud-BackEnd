package com.project.cloudstoragemanagement.dao;

import com.project.cloudstoragemanagement.entity.File;

import java.util.List;

public interface IFileDAO {
    List<File> findAllFile();
    File findByFileId(int id);
    File saveFile(File file);
    void deleteByFileId(int id);

    List<File> findByUserId(int userId);


}
