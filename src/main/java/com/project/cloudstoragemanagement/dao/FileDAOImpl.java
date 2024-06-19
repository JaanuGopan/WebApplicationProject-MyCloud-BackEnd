package com.project.cloudstoragemanagement.dao;

import com.project.cloudstoragemanagement.entity.File;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileDAOImpl implements IFileDAO{

    private EntityManager entityManager;
    @Autowired
    public FileDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<File> findAllFile() {
        TypedQuery<File> typedQuery = entityManager.createQuery("From File" , File.class);
        List<File> resultSet = typedQuery.getResultList();
        return resultSet;
    }

    @Override
    public File findByFileId(int id) {
        File file = entityManager.find(File.class,id);
        return file;
    }

    @Override
    public File saveFile(File file) {
        File savedFile = entityManager.merge(file);
        return savedFile;
    }

    @Override
    public void deleteByFileId(int id) {
        File deleteFile = entityManager.find(File.class,id);
        entityManager.remove(deleteFile);
    }

    @Override
    public List<File> findByUserId(int userId) {

        TypedQuery<File> typedQuery = entityManager.createQuery("SELECT f FROM File f WHERE userID=:userId", File.class).
                setParameter("userId", userId);
        List<File> resultSet = typedQuery.getResultList();
        return resultSet;
    }



}
