package com.example.DynamoDBApplication.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AddressConverter implements DynamoDBTypeConverter<String, Address> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convert(Address address) {
        try {
            return objectMapper.writeValueAsString(address);
        } catch (Exception e) {
            throw new RuntimeException("failed to convert Address to Json", e);
        }
    }

    @Override
    public Address unconvert(String json) {
        try {
            return objectMapper.readValue(json, Address.class);
        } catch (Exception e) {
            throw new RuntimeException("failed to convert Json to Address", e);
        }
    }

}
