package com.lixo.pos.controller;

import com.lixo.pos.model.Tax;
import com.lixo.pos.service.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/menu-items/{menuItemId}/taxes")
@RequiredArgsConstructor
public class TaxController {

    private final TaxService taxService;

    @GetMapping
    public List<Tax> getAllTax(@PathVariable Long menuItemId) {
        return taxService.getAllTax(menuItemId);
    }

    @GetMapping("/{id}")
    public Tax getTaxById(@PathVariable Long menuItemId, @PathVariable Long id) {
        return taxService.getTaxById(menuItemId, id);
    }

    @PostMapping
    public ResponseEntity<Tax> addTax(@PathVariable Long menuItemId, @RequestBody Tax tax) {

        Tax newTax = taxService.createTax(menuItemId, tax);
        return ResponseEntity.created(URI.create("/api/tax/" + newTax.getId()))
                .body(newTax);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTax(@PathVariable Long menuItemId, @PathVariable Long id) {
        taxService.deleteTax(menuItemId, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tax> updateTax(@PathVariable Long menuItemId, @PathVariable Long id, @RequestBody Tax tax) {

        Tax updatedTax = taxService.updateTax(menuItemId, id, tax);
        return ResponseEntity.ok(updatedTax);
    }
}
