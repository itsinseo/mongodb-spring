package org.example.mongodbspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory("mongodb://localhost:27017/users"));
    }

    public MongoTemplate mongoTemplate(String databaseName) {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory("mongodb://localhost:27017/" + databaseName));
    }
}
