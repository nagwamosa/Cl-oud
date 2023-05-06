package com.example.project.sa2.springboot.controller;

import com.example.project.sa2.springboot.entity.OfferDb;
import com.example.project.sa2.springboot.services.DatabaseServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ecommerceSystem")
public class ConsumerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private DatabaseServices databaseServices;

    public ConsumerController(DatabaseServices databaseServices) {
        this.databaseServices = databaseServices;
    }

//     http://localhost:8081/ecommerceSystem/list
    @GetMapping("/list")
    public List<OfferDb> getAllOffers(){
        LOGGER.info(String.format("Request of GET all offers is received successfully!"));
        return databaseServices.selectAllOffers();
    }

    //     http://localhost:8081/ecommerceSystem/offer/"any id exists in database"
    @GetMapping("/offer/{id}")
    public OfferDb getAnOffer(@PathVariable("id") int id){
        LOGGER.info(String.format("Request of GET an offer is received successfully!"));
        return databaseServices.selectOneOffer(id);
    }
}
