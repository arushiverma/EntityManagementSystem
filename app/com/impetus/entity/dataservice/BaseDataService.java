package com.impetus.entity.dataservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.impetus.datafab.core.Tube;
import com.impetus.datafab.core.tube.factory.OperationType;
import com.impetus.datafab.core.tube.factory.TubeFactory;
import com.impetus.datastore.MongoQueryExecutor;
import com.impetus.datastore.QueryExecutor;
import com.typesafe.config.Config;

public class BaseDataService { //DF controller
	
	Tube tube;
	
	QueryExecutor queryExec = new MongoQueryExecutor();
	private Config config;


	public Object find(JsonNode filter, String entity, Config conf){
		config=conf;	
		tube = TubeFactory.getTube(OperationType.FIND.getOperation());
		tube.pourData(entity, "find", filter, conf);
		String collectionName = config.getString(entity+".mongo.collection");		
		return queryExec.executeQuery("FIND", "5b30d2b359aa6436facc60c9", collectionName);
	      // findTemplate.executeQuery(consistentSubscribers, requestMap, bodyJson, response, ctx.getAuditTag(), ctx);
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
