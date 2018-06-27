package com.impetus.datastore.builder;

import com.fasterxml.jackson.databind.JsonNode;
import com.impetus.datafab.core.ValveResponse;

public interface QueryBuilder {
	
	String constructQuery(JsonNode node, String operation, ValveResponse prevResp);

}
