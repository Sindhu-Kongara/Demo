package com.example.controller;

import com.example.repository.Person;
import com.example.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @Test
    void getAllPersonsAddsPersonsToModelAndReturnsHelloWorld() {
        Person person = person(1, "Doe", "Jane");
        when(personService.getAllPersons()).thenReturn(List.of(person));
        Model model = new ExtendedModelMap();

        String viewName = personController.getAllPersons(model);

        assertEquals("HelloWorld", viewName);
        assertSame(person, ((List<?>) model.getAttribute("persons")).get(0));
        verify(personService).getAllPersons();
    }

    @Test
    void getPersonByIdReturnsPersonFromService() {
        Person person = person(4, "Smith", "Alex");
        when(personService.getPersonById(4)).thenReturn(person);

        Person result = personController.getPersonById(4);

        assertSame(person, result);
        verify(personService).getPersonById(4);
    }

    @Test
    void newPersonSavesPersonAndReturnsCreatedResponse() throws Exception {
        Person request = person(0, "Brown", "Sam");
        Person saved = person(9, "Brown", "Sam");
        when(personService.save(request)).thenReturn(saved);

        ResponseEntity<Person> response = personController.newPerson(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(URI.create("/getpersons9"), response.getHeaders().getLocation());
        assertSame(saved, response.getBody());
        ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass(Person.class);
        verify(personService).save(personCaptor.capture());
        assertSame(request, personCaptor.getValue());
    }

    private Person person(int id, String lastName, String firstName) {
        Person person = new Person();
        person.setPersonId(id);
        person.setLastName(lastName);
        person.setFirstname(firstName);
        return person;
    }
}
