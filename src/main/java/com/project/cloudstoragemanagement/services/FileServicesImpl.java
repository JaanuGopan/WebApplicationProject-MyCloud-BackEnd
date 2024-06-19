package com.project.cloudstoragemanagement.services;

import com.project.cloudstoragemanagement.dao.IFileDAO;
import com.project.cloudstoragemanagement.entity.File;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FileServicesImpl implements IFileServices{

    private IFileDAO iFileDAO;

    @Autowired
    public FileServicesImpl(IFileDAO iFileDAO) {
        this.iFileDAO = iFileDAO;
    }

    @Override
    public List<File> findAllFile() {
        return iFileDAO.findAllFile();
    }

    @Override
    public File findByFileId(int id) {
        return iFileDAO.findByFileId(id);
    }

    @Override
    @Transactional
    public File saveFile(File file) {
        return iFileDAO.saveFile(file);
    }

    @Override
    @Transactional
    public void deleteByFileId(int id) {
        iFileDAO.deleteByFileId(id);
    }

    @Override
    public List<File> findByUserId(int userId) {
        return iFileDAO.findByUserId(userId);
    }
}
