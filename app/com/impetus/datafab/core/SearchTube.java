package com.impetus.datafab.core;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.impetus.datafab.valve.Valve;
import com.impetus.datafab.valve.stream.SimpleFind;
import com.typesafe.config.Config;

public class SearchTube extends BaseTube {
	
    @Override
    public void streamToPersistentValves(List<Valve> consistentSubscribers, JsonNode bodyJson,
            TubeResponse<ValveResponse> response, final String entity, Config conf) {
        //String operation = "find"; //TODO    
    	String collectionName=conf.getString(entity+".collectionName");
        new SimpleFind().executeQuery(consistentSubscribers, bodyJson, response, collectionName);
        
    }

}
