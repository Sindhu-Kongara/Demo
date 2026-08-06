package com.example.controller;

import com.example.repository.Person;
import com.example.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping("/getpersons")
    public String getAllPersons(Model model){
       List<Person> listOfPerson= personService.getAllPersons();
       ObjectMapper mapper=new ObjectMapper();
       String listOfString=mapper.writeValueAsString(listOfPerson);
       model.addAttribute("message",listOfString);
       return "HelloWorld";

    }
    @GetMapping("/getpersons/{id}")
    public Person getPersonById(@PathVariable Integer id) {
        return personService.getPersonById(id);
    }
    @PostMapping("newperson")
    public ResponseEntity<Person> newPerson(@RequestBody Person person) throws URISyntaxException {
      Person savePerson =  personService.save(person);
      return ResponseEntity.created(new URI("/getpersons"+savePerson.getPersonId())).body(savePerson);
    }

}

