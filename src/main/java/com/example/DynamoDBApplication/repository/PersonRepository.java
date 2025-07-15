package com.example.DynamoDBApplication.repository;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.example.DynamoDBApplication.entity.Person;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PersonRepository {

    // @Autowired instead of this use @RequiredArgsConstructor and make the value
    // final
    private final DynamoDBMapper mapper;

    // Add or Update Person Details
    public Person addPerson(Person person) {
        mapper.save(person);
        return person;
    }

    public Person getPersonById(String personId) {
        return mapper.load(Person.class, personId);
    }

    public List<Person> getAllPersons() {
    return mapper.scan(Person.class, new DynamoDBScanExpression()); 
    // DynamoDBScanExpression() is used to scan to Database in Person Data
    }

    public String deletePerson(String personId) {
        Person person = mapper.load(Person.class, personId);
        if(person != null){
            mapper.delete(person);
        }
        return "person deleted";
    }

    // public String editPerson(Person person) {
    // mapper.save(person, buildExpression(person));
    // return "Person Modified";
    // }
    // private DynamoDBSaveExpression buildExpression(Person person) {
    // DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
    // Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new
    // HashMap<>();
    // expectedAttributeValueMap.put("personId", new ExpectedAttributeValue(
    // new AttributeValue().withS(person.getEmail())));
    // dynamoDBSaveExpression.setExpected(expectedAttributeValueMap);
    // return dynamoDBSaveExpression;
    // }

}
