package com.impetus.entity.dataservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.impetus.datafab.core.Tube;
import com.impetus.datafab.core.TubeResponse;
import com.impetus.datafab.core.ValveResponse;
import com.impetus.datafab.core.tube.factory.OperationType;
import com.impetus.datafab.core.tube.factory.TubeFactory;
import com.impetus.datastore.MongoQueryExecutor;
import com.impetus.datastore.QueryExecutor;
import com.typesafe.config.Config;

public class BaseDataService { //DF controller
	
	Tube tube;
	
	QueryExecutor queryExec = new MongoQueryExecutor();
	private Config config;


	public ValveResponse find(JsonNode filter, String entity, Config conf){
		config=conf;	
		tube = TubeFactory.getTube(OperationType.FIND.getOperation());
		TubeResponse<ValveResponse> response = tube.pourData(entity, OperationType.FIND.getOperation(), filter, conf);
		return response.getLastResponse();
	}
	public Object create(JsonNode node){
		tube = TubeFactory.getTube(OperationType.UPDATE.getOperation());
		return queryExec.executeQuery("CREATE", node.toString(), "Product");
	}
	public Object delete(String id){
		return queryExec.executeQuery("DELETE", id, "Product");
	}
	public Object update(JsonNode node){
		return queryExec.executeQuery("UPDATE", node.toString(), "Product");
	}
}
