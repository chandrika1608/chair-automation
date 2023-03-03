package com.lixo.pos.controller;

import com.lixo.pos.model.Offer;
import com.lixo.pos.model.Tax;
import com.lixo.pos.service.OfferService;
import com.lixo.pos.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tax")
public class OfferController {
    @Autowired
    private OfferService offerService;

    @GetMapping(value = "/get-offer")
    public List<Offer> getAllOffer() {
        return offerService.getAllOffer();
    }

    @GetMapping("offer/{id}")
    public Offer getOffer(@PathVariable String id) {
        return offerService.getOfferById(id);
    }

    @PostMapping("/save")
    public Offer createOffer(@RequestBody Offer newOffer) {
        return offerService.createOffer(newOffer);
    }
    @DeleteMapping("delete/{id}")
    public void deleteOffer(@PathVariable String id){
        OfferService.deleteOffer(id);
    }
    @PutMapping("update/{id}")
    public Offer updateOffer(@RequestBody Offer offer,@PathVariable String id){
        return offerService.updateOffer(id,offer);
    }
}
