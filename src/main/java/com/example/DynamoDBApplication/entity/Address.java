package com.example.DynamoDBApplication.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.Setter;

@DynamoDBDocument
@DynamoDBTable(tableName = "address")
@Getter
@Setter
public class Address {

    @DynamoDBAttribute
    private String districtName;

    @DynamoDBAttribute
    private String state;

    @DynamoDBAttribute
    private String pincode;
}
