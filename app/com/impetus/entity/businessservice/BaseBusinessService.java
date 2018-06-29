package com.impetus.entity.businessservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.impetus.entity.dataservice.BaseDataService;
import com.typesafe.config.Config;

public class BaseBusinessService implements EntityService{
	
	/** The core flow data service. */
    //private final BaseDataService coreFlowDataService ;

    
    @Override //TODO fetch service by factory
    public Object update(JsonNode requestBody, final String entity, Config conf) throws Exception {
        BaseDataService entityService = new BaseDataService(conf);
        return entityService.update(requestBody, entity);
    }
    
    @Override //TODO fetch service by factory
    public Object delete(String requestBody, final String entity, Config conf) throws Exception {
        BaseDataService entityService = new BaseDataService(conf);
        return entityService.delete(requestBody,entity);
    }
    
    


}
