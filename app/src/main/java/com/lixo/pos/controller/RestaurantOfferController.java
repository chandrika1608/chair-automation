package com.lixo.pos.controller;

import com.lixo.pos.model.Tax;
import com.lixo.pos.service.RestaurantTaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/offer")
public class RestaurantOfferController {

    @Autowired
    private RestaurantTaxService offerService;

    @GetMapping(value = "/get-tax")
    public List<Tax> getAllOffer() {
        return offerService.getAllTax();
    }

    @GetMapping("tax/{id}")
    public Tax getTax(@PathVariable String id) {
        return offerService.getTaxById(id);
    }

    @PostMapping("/save")
    public Tax createTax(@RequestBody Tax newTax) {
        return offerService.createTax(newTax);
    }
    @DeleteMapping("delete/{id}")
    public void deleteTax(@PathVariable String id){
        offerService.deleteTax(id);
    }
    @PutMapping("update/{id}")
    public Tax updateTax(@RequestBody Tax tax,@PathVariable String id){
        return offerService.updateTax(id,tax);
    }
}
