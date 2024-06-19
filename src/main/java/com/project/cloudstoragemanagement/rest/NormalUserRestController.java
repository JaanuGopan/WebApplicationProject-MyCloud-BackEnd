package com.project.cloudstoragemanagement.rest;

import com.project.cloudstoragemanagement.entity.NormalUser;
import com.project.cloudstoragemanagement.services.INormalUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("normaluserapi")
@RestController
@CrossOrigin("http://localhost:3000")
public class NormalUserRestController {
    private INormalUserServices iNormalUserServices;

    @Autowired
    public NormalUserRestController(INormalUserServices iNormalUserServices) {
        this.iNormalUserServices = iNormalUserServices;
    }

    @GetMapping("normalusers")
    List<NormalUser> findAllNormalUsers()
    {
        return iNormalUserServices.findAllNormalUser();
    }

    @GetMapping("normalusers/{requestedId}")
    NormalUser findNormalUserById(@PathVariable int requestedId)
    {
        NormalUser normalUser = iNormalUserServices.findByNormalUserId(requestedId);
        if(normalUser==null){
            throw new RuntimeException("Requested employee id not found");
        }
        else {
            return normalUser;
        }
    }

    @PostMapping("/normalusers")
    NormalUser addNormalUser(@RequestBody NormalUser theNormalUser)
    {
        NormalUser normalUser = iNormalUserServices.saveNormalUser(theNormalUser);
        return normalUser;
    }

    @PutMapping("/normalusers")
    NormalUser updateNormalUser(@RequestBody NormalUser theNormalUser)
    {
        NormalUser normalUser = iNormalUserServices.saveNormalUser(theNormalUser);
        return normalUser;
    }

    @DeleteMapping("/normalusers/{requestedId}")
    public String deleteNormalUserById(@PathVariable int requestedId)
    {
        NormalUser normalUser = iNormalUserServices.findByNormalUserId(requestedId);
        if(normalUser == null)
        {
            throw new RuntimeException("Requested normaluser id "+ requestedId +" not found");
        }
        else
        {
            iNormalUserServices.deleteByNormalUserId(requestedId);
        }
        return "Delete id "+requestedId+" from the database";
    }
}
