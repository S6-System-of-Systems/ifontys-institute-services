package com.appliedscience.api.web.controllers;

import com.appliedscience.api.domain.services.impl.ServiceGrabberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/service")
public class ServiceGrabberController {

    @Autowired
    private ServiceGrabberServiceImpl serviceGrabberService;

    @GetMapping("/{inumber}")
    public Map<String, Object> get(@PathVariable String inumber) {
        return serviceGrabberService.serviceData(inumber);
    }
}
