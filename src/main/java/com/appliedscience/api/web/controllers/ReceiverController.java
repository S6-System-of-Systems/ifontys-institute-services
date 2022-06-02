package com.appliedscience.api.web.controllers;

import com.appliedscience.api.data.entities.Sharepoint;
import com.appliedscience.api.domain.services.CanvasService;
import com.appliedscience.api.domain.services.SharepointService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Consumer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class ReceiverController {

    @Autowired
    private CanvasService canvasService;

    @Autowired
    private SharepointService sharepointService;

    @Autowired
    private SenderController senderController;

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive1(String in) throws InterruptedException {
        System.out.println("SHAREPOINT MESSAGE");
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive2(String in) throws InterruptedException {
        System.out.println("CANVAS MESSAGE");
    }
}
