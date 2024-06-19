package com.project.cloudstoragemanagement.dao;

import com.project.cloudstoragemanagement.entity.NormalUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.security.PrivateKey;
import java.util.List;
import java.util.PrimitiveIterator;

@Repository
public class NormalUserDAOImpl implements INormalUserDAO{

    private EntityManager entityManager;
    @Autowired
    public NormalUserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    @Override
    public List<NormalUser> findAllNormalUser() {
        TypedQuery<NormalUser> typedQuery = entityManager.createQuery("from NormalUser",NormalUser.class);
        List<NormalUser> resultSet = typedQuery.getResultList();
        return resultSet;
    }

    @Override
    public NormalUser findByNormalUserId(int id) {
        NormalUser normalUser = entityManager.find(NormalUser.class,id);
        return normalUser;
    }

    @Override
    public NormalUser saveNormalUser(NormalUser normalUser) {
        NormalUser savedNormalUser = entityManager.merge(normalUser);
        return savedNormalUser;
    }

    @Override
    public void deleteByNormalUserId(int id) {
        NormalUser normalUser = entityManager.find(NormalUser.class,id);
        entityManager.remove(normalUser);
    }
}
