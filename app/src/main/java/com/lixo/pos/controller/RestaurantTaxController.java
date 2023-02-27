package com.lixo.pos.controller;

import com.lixo.pos.model.Tax;
import com.lixo.pos.service.RestaurantTaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(name="/api/tax")
public class RestaurantTaxController  {

    @Autowired
    private RestaurantTaxService taxService;

    @GetMapping(value = "/get")
    public List<Tax> getAllTax() {
        return taxService.getAllTax();
    }

    @GetMapping("get/{id}")
    public Tax getTax(@PathVariable Long id) {
        return taxService.getTaxById(id);
    }

    @PostMapping("/save")
    public Tax createTax(@RequestBody Tax newTax) {
        return taxService.createTax(newTax);
    }
    @DeleteMapping("delete/{id}")
    public void deleteTax(@PathVariable Long id){
        taxService.deleteTax(id);
    }
    @PutMapping("update/{id}")
    public Tax updateTax(@RequestBody Tax tax,@PathVariable Long id){
        return taxService.updateTax(id,tax);
    }
}

