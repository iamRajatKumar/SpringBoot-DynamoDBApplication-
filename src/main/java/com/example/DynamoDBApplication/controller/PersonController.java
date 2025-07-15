package com.example.DynamoDBApplication.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.DynamoDBApplication.entity.Person;
import com.example.DynamoDBApplication.repository.PersonRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*") //connected with angular
public class PersonController {

    // @Autowired
    public final PersonRepository personRepository;

    @PostMapping("/person")
    public Person savePerson(@RequestBody Person person){
        return personRepository.addPerson(person);
    }

    @GetMapping("/person")
    public List<Person> getAllPersons(){
        return personRepository.getAllPersons();
    }

    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable("id") String personId){
        return personRepository.getPersonById(personId);
    }

    @PutMapping("/person/{id}")
    public Person updatePerson(@PathVariable String id, @RequestBody Person updatedPerson){
        Person existingPerson= personRepository.getPersonById(id);
            if(existingPerson != null) {
            //update code
            existingPerson.setName(updatedPerson.getName());
            existingPerson.setEmail(updatedPerson.getEmail());
            existingPerson.setAddress(updatedPerson.getAddress());
            return personRepository.addPerson(existingPerson);
            }
        return null;
    }

    //Delete
    @DeleteMapping("/person/{id}")
    public String deletePerson(@PathVariable String id){
        personRepository.deletePerson(id);
        return "Person Deleted Successfully: "+id;
    }

}
