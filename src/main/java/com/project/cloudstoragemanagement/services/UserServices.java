package com.project.cloudstoragemanagement.services;

import com.project.cloudstoragemanagement.dao.IUserDAO;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices implements IUserServices {
    private IUserDAO iUserDAO;

    @Autowired
    public UserServices(IUserDAO iUserDAO) {
        this.iUserDAO = iUserDAO;
    }

    @Override
    public List<User> findAll() {
        return iUserDAO.findAll();
    }

    @Override
    public User findById(int id) {
        return iUserDAO.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        User savedUser = iUserDAO.save(user);
        return savedUser;
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        iUserDAO.deleteById(id);
    }
}
