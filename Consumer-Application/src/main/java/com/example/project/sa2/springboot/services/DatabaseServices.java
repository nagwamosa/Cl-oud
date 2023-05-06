package com.example.project.sa2.springboot.services;

import com.example.project.sa2.springboot.entity.OfferDb;
import com.example.project.sa2.springboot.model.Offer;
import com.example.project.sa2.springboot.repository.OfferRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatabaseServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseServices.class);

    @Autowired
    private OfferRepo offerRepo;

    public DatabaseServices(OfferRepo offerRepo) {
        this.offerRepo = offerRepo;
    }

    public void insertOffer(Offer offer){

        OfferDb offerDb = new OfferDb();
        offerDb.setDescription(offer.getDescription());
        offerDb.setProduct(offer.getProduct());

        offerRepo.save(offerDb);

        LOGGER.info(String.format("New offer is inserted successfully in database! -> %s", offerDb.toString()));
    }

    public List<OfferDb> selectAllOffers(){
        LOGGER.info(String.format("All offers are selected successfully from database!"));
        return offerRepo.findAll();
    }

    public OfferDb selectOneOffer(int id){
        Optional<OfferDb> offerDb = offerRepo.findById(id);
        LOGGER.info(String.format("An offer is selected successfully from database! id -> %d", id));
        return offerDb.orElse(null);
    }

    public void deleteAnOffer(int id){
        LOGGER.info(String.format("An offer is deleted successfully from database! id -> %d", id));
        offerRepo.deleteById(id);
    }

    public OfferDb updateAnOffer(Offer newOffer){
        Optional<OfferDb> oldOffer = offerRepo.findById(newOffer.getId());
        if (oldOffer.isPresent()){
            OfferDb offer = oldOffer.get();

            offer.setId(newOffer.getId());
            offer.setDescription(newOffer.getDescription());
            offer.setDescription(newOffer.getDescription());

            LOGGER.info(String.format("An offer is Updated successfully in database! -> %s", offer.toString()));
            return offerRepo.save(offer);
        }else {

            LOGGER.info(String.format("An offer is not exist in database!"));
            return null;
        }
    }
}
