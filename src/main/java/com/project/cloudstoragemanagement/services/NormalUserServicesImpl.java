package com.project.cloudstoragemanagement.services;

import com.project.cloudstoragemanagement.dao.INormalUserDAO;
import com.project.cloudstoragemanagement.entity.NormalUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NormalUserServicesImpl implements INormalUserServices {

    private INormalUserDAO iNormalUserDAO;

    @Autowired
    public NormalUserServicesImpl(INormalUserDAO iNormalUserDAO) {
        this.iNormalUserDAO = iNormalUserDAO;
    }

    @Override
    public List<NormalUser> findAllNormalUser() {
        return iNormalUserDAO.findAllNormalUser();
    }

    @Override
    public NormalUser findByNormalUserId(int id) {
        return iNormalUserDAO.findByNormalUserId(id);
    }

    @Override
    @Transactional
    public NormalUser saveNormalUser(NormalUser normalUser) {
        return iNormalUserDAO.saveNormalUser(normalUser);
    }

    @Override
    @Transactional
    public void deleteByNormalUserId(int id) {
        iNormalUserDAO.deleteByNormalUserId(id);
    }
}
