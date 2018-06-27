package com.impetus.datafab.core;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.impetus.datafab.valve.Valve;
import com.typesafe.config.Config;

@SuppressWarnings("rawtypes")
public class SyncTube extends BaseTube {
	
	 @Override
	    protected void streamToPersistentValves (List<Valve> consistentSubscribers, JsonNode bodyJson,
	            TubeResponse<ValveResponse> response, String entity, Config conf) {}


}
