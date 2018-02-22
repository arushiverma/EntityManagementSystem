package com.target.entity.dataservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.target.datastore.MongoQueryExecutor;
import com.target.datastore.QueryExecutor;

public class BaseDataService {
	//TODO
	//Tube tube = TubeFactory.getTube(operation, source);
	
	QueryExecutor queryExec = new MongoQueryExecutor();
	/*
	public Object find(String id){
		
		return queryExec.executeQuery("FIND", id, "app.product");
	      // findTemplate.executeQuery(consistentSubscribers, requestMap, bodyJson, response, ctx.getAuditTag(), ctx);
	}*/
	
	public Object find(String id){
		
		return queryExec.executeQuery("FIND", id, "Product");
	      // findTemplate.executeQuery(consistentSubscribers, requestMap, bodyJson, response, ctx.getAuditTag(), ctx);
	}
	public Object create(JsonNode node){
		return queryExec.executeQuery("CREATE", node.toString(), "Product");
	}
	public Object delete(String id){
		return queryExec.executeQuery("DELETE", id, "Product");
	}
	public Object update(JsonNode node){
		return queryExec.executeQuery("UPDATE", node.toString(), "Product");
	}
}
