package com.lixo.pos.controller;

import com.lixo.pos.model.Kitchen;
import com.lixo.pos.service.RestaurantKitchenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/kitchen")
public class RestaurantKitchenController {
    @Autowired
    private RestaurantKitchenService kitchenService;

    @GetMapping(value = "/getkitchen")
    public List<Kitchen> getAllKitchen() {
        return kitchenService.getAllKitchen();
    }
    @GetMapping("retreive/{id}")
    public Kitchen getKitchen(@PathVariable String id) {
        return kitchenService.getKitchenById(id);
    }
    @PostMapping("/save")
    public ResponseEntity<Kitchen> createKitchen(@RequestBody @Valid Kitchen newKitchen) throws Exception
    {
        try {

            Kitchen newKitchenn = kitchenService.createKitchen(newKitchen);
            return ResponseEntity.created(URI.create("/api/kitchen/" + newKitchenn.getId()))
                    .body(newKitchenn);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<>(errorMessages.toString(), HttpStatus.BAD_REQUEST);
    }



    @DeleteMapping("delete/{id}")
    public void deleteKitchen(@PathVariable String id){
        kitchenService.deleteKitchen(id);
    }
    @PutMapping("update/{id}")
    public Kitchen updateKitchen(@RequestBody Kitchen kitchen,@PathVariable String id){
        return kitchenService.updateKitchen(id,kitchen);
    }

}
