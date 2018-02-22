package com.target.entity.businessservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.target.entity.dataservice.BaseDataService;

public interface EntityService {
	
	final BaseDataService dataService = new BaseDataService();
	
	//TODO create own exception
	default Object find(String id) throws Exception {
		return dataService.find(id);
	}
	
	Object update(JsonNode requestBody) throws Exception;
	
	Object delete(String requestBody) throws Exception;
	
	default Object create(JsonNode requestBody) throws Exception{
		return dataService.create(requestBody);
	}


}
