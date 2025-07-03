// package com.example.DynamoDBApplication.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import com.amazonaws.auth.AWSStaticCredentialsProvider;
// import com.amazonaws.auth.BasicAWSCredentials;
// import com.amazonaws.client.builder.AwsClientBuilder;
// import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
// import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
// import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

// @Configuration
// public class DynamoDbConfig {

//     @Bean
//     public DynamoDBMapper mapper(){
//         return new DynamoDBMapper(dynamoDBMapperConfig());
//     }

//     private AmazonDynamoDB dynamoDBMapperConfig(){
//         return AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
//                 new AwsClientBuilder.EndpointConfiguration("aws.dynamodb.endpoint", "aws.region"))
//                 .withCredentials((new AWSStaticCredentialsProvider(new BasicAWSCredentials("aws.accessKey","aws.secretKey"))))
//                 .build();
//     }

// }

package com.example.DynamoDBApplication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDbConfig {

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Value("${aws.dynamodb.endpoint:}") // Optional (empty if not provided)
    private String endpoint;

    @Bean
    public DynamoDBMapper mapper() {
        return new DynamoDBMapper(dynamoDBClient());
    }

    private AmazonDynamoDB dynamoDBClient() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials));

        if (endpoint != null && !endpoint.isBlank()) {
            builder.withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration(endpoint, region));
        } else {
            builder.withRegion(region);
        }

        return builder.build();
    }
}
