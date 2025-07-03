package com.example.DynamoDBApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.DynamoDBApplication.entity.Person;
import com.example.DynamoDBApplication.repository.PersonRepository;

// import lombok.RequiredArgsConstructor;

@RestController
// @RequiredArgsConstructor
public class PersonController {

    @Autowired
    public PersonRepository personRepository;

    @PostMapping("/person")
    public Person savePerson(@RequestBody Person person){
        return personRepository.addPerson(person);
    }

    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable("id") String personId){
        return personRepository.getPerson(personId);
    }


}
