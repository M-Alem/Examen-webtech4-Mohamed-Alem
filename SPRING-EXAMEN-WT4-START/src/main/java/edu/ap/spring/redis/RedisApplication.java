package edu.ap.spring.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class RedisApplication{
    private RedisService service;
    private String CHANNEL = "edu:ap:spring:redis";
    private String KEY = "edu:ap:test";

    @Autowired
    public void setRedisService(RedisService service) {this.service = service;}

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return (args) -> {
            // Messaging
            service.sendMessage(CHANNEL, "Hello from Spring Boot");

            // Keys
            service.setKey(KEY, "Key from Spring Boot");
            System.out.println(service.getKey(KEY));

            // Bitops
            String bitKey = "edu:ap:bits";
            service.setBit(bitKey, 73, true);
            service.setBit(bitKey, 85, true);
            service.setBit(bitKey, 90, true);
            System.out.println(bitKey + ", bit 73 : " + service.getBit(bitKey, 73));
            System.out.println(bitKey + ", bit count : " + service.bitCount(bitKey));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
