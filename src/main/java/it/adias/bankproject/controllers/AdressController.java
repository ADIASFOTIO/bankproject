package it.adias.bankproject.controllers;

import it.adias.bankproject.model.dto.AdressDto;
import it.adias.bankproject.model.services.abstractions.AdressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adresses")
@RequiredArgsConstructor
public class AdressController {
    private final AdressService service;
    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody AdressDto adressDto){
        return ResponseEntity.ok(service.save(adressDto));
    }
    @GetMapping("/")
    public ResponseEntity<List<AdressDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{adress-id}")
    public ResponseEntity<AdressDto> findById(@PathVariable("adress-id") Integer adressId){
        return ResponseEntity.ok(service.findById(adressId));
    }
    @DeleteMapping("/{adress-id}")
    public ResponseEntity<Void> delete(@PathVariable("adress-id") Integer adressId){
        service.delete(adressId);
        return ResponseEntity.accepted().build();
    }
}
