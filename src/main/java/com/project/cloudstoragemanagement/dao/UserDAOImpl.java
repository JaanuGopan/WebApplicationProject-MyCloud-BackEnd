package com.project.cloudstoragemanagement.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements IUserDAO{

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findAll() {
        //JPQL
        TypedQuery<User> typedQuery = entityManager.createQuery("from User",User.class);
        List<User> resultSet = typedQuery.getResultList();
        return  resultSet;
    }

    @Override
    public User findById(int id) {
        User user  = entityManager.find(User.class,id);
        return user;
    }

    @Override
    public User save(User user) {
        User savedUser= entityManager.merge(user);
        return savedUser;
    }

    @Override
    public void deleteById(int id) {
        User deleteUser = entityManager.find(User.class,id);
        entityManager.remove(deleteUser);
    }
}
