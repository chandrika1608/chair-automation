package com.lixo.pos.controller;

import com.lixo.pos.model.Kitchen;
import com.lixo.pos.model.Tax;
import com.lixo.pos.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/menuitems/{menuItemId}/taxes")
public class TaxController {
    @Autowired
    private TaxService taxService;

    @GetMapping(value = "/get-tax")
    public List<Tax> getAllTax(@PathVariable Long menuItemId) {
        return taxService.getAllTax(menuItemId);
    }

    @GetMapping("/tax/{id}")
    public Tax getTaxById(@PathVariable Long menuItemId,@PathVariable Long id)
    {
        return taxService.getTaxById(menuItemId,id);
    }

    @PostMapping
    public ResponseEntity<Tax> addTax(@PathVariable Long menuItemId,@RequestBody Tax tax) {

        Tax newTax = taxService.createTax(menuItemId,tax);
        return ResponseEntity.created(URI.create("/api/tax/" + newTax.getId()))
                .body(newTax);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteTax(@PathVariable Long menuItemId,@PathVariable Long id)
    {
        taxService.deleteTax(menuItemId,id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("update/{id}")
    public  ResponseEntity<Tax> updateTax(@PathVariable Long menuItemId,@PathVariable Long id,@RequestBody Tax tax){

        Tax updatedTax= taxService.updateTax(menuItemId,id, tax);
        return ResponseEntity.ok(updatedTax);
    }
}
