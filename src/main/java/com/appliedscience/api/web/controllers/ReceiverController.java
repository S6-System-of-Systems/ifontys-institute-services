package com.appliedscience.api.web.controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class ReceiverController {

    @RabbitListener(queues = "ifontys.queue")
    public void receive(String in) throws InterruptedException {

        System.out.println("Received '" + in + "'");
    }
}
