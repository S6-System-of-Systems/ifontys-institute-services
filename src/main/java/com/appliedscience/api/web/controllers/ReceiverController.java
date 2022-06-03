package com.appliedscience.api.web.controllers;

import com.appliedscience.api.data.entities.Canvas;
import com.appliedscience.api.data.entities.Sharepoint;
import com.appliedscience.api.domain.exceptions.CanvasNotFoundException;
import com.appliedscience.api.domain.exceptions.SharepointNotFoundException;
import com.appliedscience.api.domain.services.CanvasService;
import com.appliedscience.api.domain.services.SharepointService;
import com.appliedscience.api.web.mapper.CanvasMapper;
import com.appliedscience.api.web.mapper.SharepointMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiverController {

    @Autowired
    private CanvasService canvasService;

    @Autowired
    private SharepointService sharepointService;

    @Autowired
    private CanvasMapper canvasMapper;

    @Autowired
    private SharepointMapper sharepointMapper;

    @Autowired
    private SenderController senderController;

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive1(String in) throws InterruptedException, SharepointNotFoundException, CanvasNotFoundException {
        System.out.println("FHICT MESSAGE");
        received(in);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive2(String in) throws InterruptedException {
        System.out.println("FHKE MESSAGE");
    }


    private void received(String in) {
        Map<String, Object> results = new HashMap<>();

        final var optionalSharepoint = sharepointService.findByUsername(in);
        if(!optionalSharepoint.isEmpty()) {
            final var sharepoint = optionalSharepoint.get();
            var sharepointDto = sharepointMapper.toDto(sharepoint);
            results.put("sharepoint", sharepointDto);
        }
        final var optionalCanvas = canvasService.findById(in);
        if(!optionalCanvas.isEmpty()) {
            results.put("canvas", canvasMapper.toDto(optionalCanvas.get()));
        }
        try {
            FileOutputStream fos = new FileOutputStream("listData");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(results);
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        senderController.send(results);
    }
}
