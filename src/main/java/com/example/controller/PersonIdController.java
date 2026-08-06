package com.example.controller;

import com.example.repository.Person;
import com.example.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class PersonIdController {
    private final PersonService personService;

    public PersonIdController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping("/getpersonid")
    public Collection<Person> id(@RequestParam int id){
        return personService.getAllPersons();
    }

}
