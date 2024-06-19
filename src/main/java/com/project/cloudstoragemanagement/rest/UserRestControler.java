package com.project.cloudstoragemanagement.rest;

import com.project.cloudstoragemanagement.services.IUserServices;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api")
@RestController
public class UserRestControler {
    private IUserServices iuserServices;

    @Autowired
    public UserRestControler(IUserServices iUserServices) {
        this.iuserServices = iUserServices;
    }

    @GetMapping("users")
    List<User> findAllUsers()
    {
        List<User> user=iuserServices.findAll();
        return user;
    }

    @GetMapping("users/{id}")
    User findUserById(@PathVariable int id){
        User user = iuserServices.findById(id);
        if(user == null) throw new RuntimeException("Requested employee id not found");
        else{
            return user;
        }
    }

    @PostMapping("/users")
    User addEmployee(@RequestBody User theUser)
    {
        User savedUser = iuserServices.save(theUser);

        return savedUser;
    }

    @PutMapping("/users")
    User updateEmployee(@RequestBody User theUser)
    {
        //if it's there it will update the existing one
        //if it's not there it will create a new record in the database
        User user = iuserServices.save(theUser);
        return user;
    }

    @DeleteMapping("/users/{requestedId}")
    public String deleteEmployeeById(@PathVariable int requestedId)
    {
        User user = iuserServices.findById(requestedId);
        if(user == null)
        {
            throw new RuntimeException("Requested user id "+ requestedId +" not found");
        }
        else
        {
            iuserServices.deleteById(requestedId);
        }

        return "Deleted id "+requestedId+" from the database";
    }









}
