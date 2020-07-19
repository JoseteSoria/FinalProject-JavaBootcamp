package com.ironhack.PharmacyEdge.service;

import com.ironhack.PharmacyEdge.client.UserClient;
import com.ironhack.PharmacyEdge.model.user.User;
import com.ironhack.PharmacyEdge.model.user.viewModel.UserVM;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @Autowired
    private UserClient userClient;

    @HystrixCommand(fallbackMethod = "errorGetAll")
    public List<UserVM> getAll(){
        LOGGER.info("GET request to retrieve every user");
        return userClient.getAll();
    }

    public List<UserVM> errorGetAll() {
        LOGGER.error("Controlled exception - fail in GET request to retrieve all the salesReps. ");
        throw new SalesRepServiceDownException("SalesRep Service Down. Method getAll. ");
    }

    @HystrixCommand(fallbackMethod = "errorGetById")
    public SalesRepVM getById(Integer id){
        LOGGER.info("GET request to retrieve sales rep with id " + id);
        return userClient.getById(id);
    }

    public SalesRepVM errorGetById(Integer id) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve the salesReps with id " +id);
        throw new SalesRepServiceDownException("SalesRep Service Down. Method getById. ");
    }

    @HystrixCommand(fallbackMethod = "errorCreate", ignoreExceptions = {RuntimeException.class})
    public SalesRepVM create(SalesRepReqBody newSalesRep){
        LOGGER.info("POST request to create a new sales rep");
        return addSalesRepLeadClient.create(newSalesRep);
    }

    public SalesRepVM errorCreate(SalesRepReqBody newSalesRep){
        LOGGER.error("Controlled exception - fail in POST request to create a new salesRep id. ");
        throw new AddSalesRepLeadServiceDownException("SalesRep-Leads Service Down. Method create. ");
    }

    @HystrixCommand(fallbackMethod = "errorDelete")
    public void delete(Integer id) {
        LOGGER.info("DELETE request to remove sales rep with id " + id);
        userClient.delete(id);
    }

    public void errorDelete(Integer id) {
        LOGGER.error("Controlled exception - fail in DELETE request to remove a new salesRep id. " + id);
        throw new SalesRepServiceDownException("SalesRep Service Down. Method delete. ");
    }

    @Override
    @HystrixCommand(fallbackMethod = "errorLoadUserByUsername")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Load user by username " + username);
        Optional<SalesRep> user = userClient.findByUsername(username);
        System.out.println("Usuario buscado");
        return new CustomSecurityUser(user.orElseThrow(() ->
                new UsernameNotFoundException("Invalid username/password combination.")));
    }

    public UserDetails errorLoadUserByUsername(String username){
        LOGGER.error("Controlled exception - Fail in Authorization to find user (salesRep) with name " + username);
        throw new SalesRepServiceDownException("SalesRep Service Down. Method loadUserByUsername. ");
    }

}
