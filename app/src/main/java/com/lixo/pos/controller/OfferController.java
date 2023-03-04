package com.lixo.pos.controller;

import com.lixo.pos.model.Offer;
import com.lixo.pos.model.Offer;
import com.lixo.pos.service.OfferService;
import com.lixo.pos.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offer")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping
    public List<Offer> getAllOffer() {
        return offerService.getAllOffer();
    }

    @GetMapping("/{id}")
    public Offer getOffer(@PathVariable Long id) {
        return offerService.getOfferById(id);
    }

    @PostMapping
    public Offer createOffer(@RequestBody Offer newOffer) {
        return offerService.createOffer(newOffer);
    }
    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable Long id){
        OfferService.deleteOffer(id);
    }
    @PutMapping("/{id}")
    public Offer updateOffer(@RequestBody Offer offer,@PathVariable Long id){
        return offerService.updateOffer(id,offer);
    }
}
