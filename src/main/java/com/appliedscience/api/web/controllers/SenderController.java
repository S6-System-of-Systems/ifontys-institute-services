package com.appliedscience.api.web.controllers;

import com.appliedscience.api.web.eventlog.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.json4s.jackson.Json;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import scala.util.parsing.json.JSON;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class SenderController {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private TopicExchange topic;



    @Value("${ifontys.endpoint.key}")
    private String key;



    public void send(Map<String, Object> object) {

        Gson gson = new Gson();
        Logger logger = new Logger();
        JSONObject metadata = logger.AddLog("");
        JSONObject returnObject = new JSONObject();
        returnObject.put("metadata", metadata);
        returnObject.put("data",new JSONObject(object));

        template.convertAndSend(topic.getName(), key, returnObject.toString());
        System.out.println(" [x] Sent '" + returnObject.toString() + "'");
    }
}
