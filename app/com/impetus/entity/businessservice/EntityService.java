package com.impetus.entity.businessservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.impetus.entity.dataservice.BaseDataService;
import com.typesafe.config.Config;

public interface EntityService {
	
	final BaseDataService dataService = new BaseDataService();
	
	//TODO create own exception
	default Object find(JsonNode requestBody, String entity, Config conf) throws Exception {
		return dataService.find(requestBody, entity, conf);
	}
	
	Object update(JsonNode requestBody,final String entity, Config conf) throws Exception;
	
	Object delete(String requestBody, final String entity, Config conf) throws Exception;
	
	default Object create(JsonNode requestBody, String entity, Config conf) throws Exception{
		return dataService.create(requestBody, entity, conf);
	}


}
