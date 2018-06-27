package com.impetus.datafab.valve.stream;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.impetus.datafab.core.TubeResponse;
import com.impetus.datafab.core.ValveResponse;
import com.impetus.datafab.core.tube.factory.OperationType;
import com.impetus.datafab.valve.Valve;
import com.impetus.datastore.QueryExecutor;
import com.impetus.datastore.builder.QueryBuilder;
import com.impetus.entity.constants.EntityConstants;

public class SimpleFind implements Find {

	@Override
	public void executeQuery(List<Valve> subscribers, JsonNode bodyJson, TubeResponse<ValveResponse> response, String collection) {
		// call transformer and executor here
		for (Valve subscriber : subscribers) {
            System.out.println("Syncing State with: " + subscriber.getName());
            String operation=OperationType.FIND.getOperation();
            //Build using filter
            QueryBuilder qb=subscriber.getQueryBuilder();
            List<JsonNode> firstRes;
            if(subscriber.getDependency()!=null && !isFindOne(bodyJson)) {            	
            	operation=OperationType.FIND_ONE.getOperation();            	
            } 
            String query = qb.constructQuery(bodyJson, operation, response.getFirstResponse());
            //execute
            QueryExecutor queryExec = subscriber.getQueryExecutor();
            
            ValveResponse result = queryExec.executeQuery(operation, query , collection);
            response.addResponse(result);
            
        }
	}
	
	
	public boolean isFindOne(JsonNode node) {
		ObjectNode req = (ObjectNode)node.get("filter");
		if(req.has(EntityConstants.ID)) {
			return true;
		}
		return false;
	}

}
