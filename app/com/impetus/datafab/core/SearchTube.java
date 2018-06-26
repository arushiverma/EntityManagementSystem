package com.impetus.datafab.core;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.impetus.datafab.valve.Valve;
import com.impetus.datafab.valve.stream.SimpleFind;

public class SearchTube extends BaseTube {
	
    @Override
    public void streamToPersistentValves(List<Valve> consistentSubscribers, JsonNode bodyJson,
            TubeResponse<ValveResponse> response) {
        String operation = "find"; //TODO        
        new SimpleFind().executeQuery(consistentSubscribers, bodyJson, response);
    }

}
