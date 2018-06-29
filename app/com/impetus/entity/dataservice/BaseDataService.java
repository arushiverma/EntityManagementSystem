package com.impetus.entity.dataservice;

import java.util.List;

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
	Config conf = null;
	
	public BaseDataService() {}
	public BaseDataService(Config conf) {
		this.conf=conf;
	}
	
	Tube tube;
	
	QueryExecutor queryExec = new MongoQueryExecutor();
	private Config config;


	public List<JsonNode> find(JsonNode filter, String entity, Config conf){
		config=conf;	
		tube = TubeFactory.getTube(OperationType.FIND.getOperation());
		TubeResponse<ValveResponse> response = tube.pourData(entity, OperationType.FIND.getOperation(), filter, conf);
		return response.getLastResponse().getResponses();
	}
	public Object create(JsonNode node, String entity, Config conf){
		String collectionName=conf.getString(entity+".collectionName");
		tube = TubeFactory.getTube(OperationType.UPDATE.getOperation());
		return queryExec.executeQuery(OperationType.CREATE.getOperation(), node.toString(), collectionName);
	}
	public Object delete(String id, String entity){
		String collectionName=conf.getString(entity+".collectionName");
		return queryExec.executeQuery(OperationType.DELETE.getOperation(), id, collectionName);
	}
	public Object update(JsonNode node,String entity){
		String collectionName=conf.getString(entity+".collectionName");
		return queryExec.executeQuery(OperationType.UPDATE.getOperation(), node.toString(), collectionName);
	}
}
