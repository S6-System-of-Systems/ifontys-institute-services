package com.appliedscience.api.web.eventlog;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json4s.jackson.Json;

import java.time.LocalDateTime;

public class Logger {

    public Logger(){}

    public JSONObject AddLog(String metaData){
        JSONObject metadata = new JSONObject();
        JSONArray tracer = new JSONArray();
        JSONObject trace = new JSONObject("{'microservice': 'ifontys-institute-services', 'date': '"+ LocalDateTime.now() + "'}");
        tracer.put(trace);
        metadata.put("tracer", tracer);
        return metadata;
    }

}
