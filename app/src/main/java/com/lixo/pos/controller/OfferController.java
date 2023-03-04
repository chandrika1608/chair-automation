package com.lixo.pos.controller;

import com.lixo.pos.model.Offer;
import com.lixo.pos.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<Offer> addOffer(@RequestBody Offer offer) {
        Offer newOffer = offerService.createOffer(offer);
        return ResponseEntity.created(URI.create("/api/offer/" + newOffer.getId()))
                .body(newOffer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredients(@PathVariable Long id) {
        offerService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable Long id, @RequestBody Offer offer) {
        Offer updatedOffer = offerService.updateOffer(id, offer);
        return ResponseEntity.ok(updatedOffer);
    }
}
