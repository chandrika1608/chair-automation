package com.lixo.pos.controller;

import com.lixo.pos.model.Tax;
import com.lixo.pos.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tax")
public class TaxController {
    @Autowired
    private TaxService taxService;

    @GetMapping(value = "/get-tax")
    public List<Tax> getAllTax() {
        return taxService.getAllTax();
    }

    @GetMapping("tax/{id}")
    public Tax getTax(@PathVariable String id) {
        return taxService.getTaxById(id);
    }

    @PostMapping("/save")
    public Tax createTax(@RequestBody Tax newTax) {
        return taxService.createTax(newTax);
    }
    @DeleteMapping("delete/{id}")
    public void deleteTax(@PathVariable String id){
        TaxService.deleteTax(id);
    }
    @PutMapping("update/{id}")
    public Tax updateTax(@RequestBody Tax tax,@PathVariable String id){
        return taxService.updateTax(id,tax);
    }
}
