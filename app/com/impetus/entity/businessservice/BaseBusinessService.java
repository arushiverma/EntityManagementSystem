package com.impetus.entity.businessservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.impetus.entity.dataservice.BaseDataService;

public class BaseBusinessService implements EntityService{
	
	/** The core flow data service. */
    //private final BaseDataService coreFlowDataService ;

    
    @Override //TODO fetch service by factory
    public Object update(JsonNode requestBody) throws Exception {
        BaseDataService entityService = new BaseDataService();
        return entityService.update(requestBody);
    }
    
    @Override //TODO fetch service by factory
    public Object delete(String requestBody) throws Exception {
        BaseDataService entityService = new BaseDataService();
        return entityService.delete(requestBody);
    }
    
    


}
