package it.adias.bankproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @PostMapping("/")
    public void save(){
    }
    @GetMapping("/")
    public void findAll(){
    }
    @GetMapping("/{id}")
    public void findById(@PathVariable("id") Integer userId){

    }

}
