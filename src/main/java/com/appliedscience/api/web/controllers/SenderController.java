package com.appliedscience.api.web.controllers;

import com.google.gson.Gson;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class SenderController {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private TopicExchange topic;

    @Value("${ifontys.endpoint.key}")
    private String key;

    public void send(Object object) {
        Gson gson = new Gson();
        template.convertAndSend(topic.getName(), key, gson.toJson(object));
        System.out.println(" [x] Sent '" + gson.toJson(object) + "'");
    }
}
