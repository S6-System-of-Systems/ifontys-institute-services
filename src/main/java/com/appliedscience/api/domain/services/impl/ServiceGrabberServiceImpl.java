package com.appliedscience.api.domain.services.impl;

import com.appliedscience.api.domain.services.CanvasService;
import com.appliedscience.api.domain.services.SharepointService;
import com.appliedscience.api.web.mapper.CanvasMapper;
import com.appliedscience.api.web.mapper.SharepointMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class ServiceGrabberServiceImpl {

    @Autowired
    private CanvasService canvasService;

    @Autowired
    private SharepointService sharepointService;

    @Autowired
    private CanvasMapper canvasMapper;

    @Autowired
    private SharepointMapper sharepointMapper;

    public Map<String, Object> serviceData(String inumber) {
        Map<String, Object> results = new HashMap<>();
        Gson gson = new Gson();

        final var optionalSharepoint = sharepointService.findByUsername(inumber.toUpperCase());
        if(!optionalSharepoint.isEmpty()) {
            final var sharepoint = optionalSharepoint.get();
            var sharepointDto = sharepointMapper.toDto(sharepoint);
//            System.out.println(gson.toJson(sharepoint));
            results.put("sharepoint", sharepointDto);
        }
        final var optionalCanvas = canvasService.findById(inumber.toLowerCase());
        if(!optionalCanvas.isEmpty()) {
            final var canvas = optionalCanvas.get();
            var canvasDto = canvasMapper.toDto(canvas);
            results.put("canvas", canvasDto);
            System.out.println(canvas);
        }

        System.out.println(gson.toJson(results));
        return results;
    }
}
