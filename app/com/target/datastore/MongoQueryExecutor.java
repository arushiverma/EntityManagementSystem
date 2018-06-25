package com.target.datastore;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.target.datastore.utils.QueryUtil;
import com.typesafe.config.Config;

public class MongoQueryExecutor implements QueryExecutor{

	private MongoClient mongo;
	private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
	private Config config = null;

	
	
	public ObjectNode executeQuery(String operation, String query, String entity){
		ObjectNode result=null;
		if (operation != null) {
            switch (operation) {
                case "FIND":
                	result = executeFindQuery(query, entity);                	
                    break;
                case "UPDATE":
                	result = executeUpdateQuery(query, entity);                	
                    break;
                case "CREATE":
                	result = executeCreateQuery(query, entity);
                	break;
                case "DELETE":
                	result = executeDeleteQuery(query, entity);
                	break;
                default:
                    break;
                    }
            }
		return result;
		
		
	}
	private ObjectNode executeFindQuery(String query, String entity){
		ObjectNode queryNode = constructQuery(query);
		List<ObjectNode> responses = new ArrayList<ObjectNode>();
		DB conn = createMongoConnection("appdata");
		DBObject dbResponse;
		//String collectionName = config.getString(entity+"mongo.collection");
		DBCollection table = conn.getCollection("app.products");//TODO get table by entity
		BasicDBObject searchQuery = (BasicDBObject) JSON.parse(queryNode.toString());
		
		ObjectNode node = OBJECTMAPPER.createObjectNode();
		DBCursor cursor = table.find(searchQuery);
		int recCount = cursor.size();
		while (cursor.hasNext()) {
			
			dbResponse = cursor.next();
			
			
			
            try {
				node = OBJECTMAPPER.readValue(dbResponse.toString(), ObjectNode.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            QueryUtil.parseResult(node, null, null);
            //responses.add(node);
            System.out.println(" Found " + recCount+ " records");
            
		}
		return node;
		
		
	}
	
	private ObjectNode executeUpdateQuery(String query, String entity){
		ObjectNode updateResp = OBJECTMAPPER.createObjectNode();
		query = QueryUtil.parseRequest(query);
		if (query!=null) {
			DBObject document = (BasicDBObject) JSON.parse(query);
			DB conn = createMongoConnection("config");
			DBCollection dbCollection = conn.getCollection("app.products");
			WriteResult result = null;
			if (dbCollection != null) {
				result = dbCollection.save(document);
			}
			if (result != null && result.getError() == null) {
				try {
					updateResp = (ObjectNode) OBJECTMAPPER.readValue(result.toString(), JsonNode.class);
					System.out.println("Updated object ");
					QueryUtil.parseResult(updateResp, null, null);
					//result.addResponse(docJson);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		}
		return updateResp;
	}
	
	private ObjectNode executeCreateQuery(String query, String entity){
		ObjectNode updateResp = OBJECTMAPPER.createObjectNode();
		query = QueryUtil.parseRequest(query);
		DBObject document =  (BasicDBObject) JSON.parse(query);
		DB conn = createMongoConnection("config");
		DBCollection dbCollection = conn.getCollection("app.products");
		WriteResult result = null;
		if (dbCollection != null) {
			result = dbCollection.insert(document);
		}
		if (result != null && result.getError() == null) {
			try {
				updateResp = (ObjectNode) OBJECTMAPPER.readValue(result.toString(), JsonNode.class);
				System.out.println("Updated object ");
				updateResp.put("message", "Updated " +updateResp.get("n") + " records");
				QueryUtil.parseResult(updateResp, null, null);
				//result.addResponse(docJson);
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return updateResp;
	}
	
	private ObjectNode executeDeleteQuery(String id, String entity){
		ObjectNode updateResp = OBJECTMAPPER.createObjectNode();
		ObjectNode queryNode = constructQuery(id);
		//id = QueryUtil.parseRequest(id);
		if (queryNode!=null) {
			//DBObject document = (BasicDBObject) JSON.parse(queryNode);
			BasicDBObject searchQuery = (BasicDBObject) JSON.parse(queryNode.toString());
			DB conn = createMongoConnection("config");
			DBCollection dbCollection = conn.getCollection("app.products");
			WriteResult result = null;
			if (dbCollection != null) {
				result = dbCollection.remove(searchQuery);
			}
			if (result != null && result.getError() == null) {
				try {
					updateResp = (ObjectNode) OBJECTMAPPER.readValue(result.toString(), JsonNode.class);
					updateResp.put("message", "Deleted " +updateResp.get("n") + " records");
					System.out.println("deleted object ");
					//QueryUtil.parseResult(updateResp, null, null);
					//result.addResponse(docJson);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		}
		return updateResp;
	}
	private DB createMongoConnection(String database){
		if (mongo==null){
			synchronized(this){
				if(mongo==null){
					try {
						mongo =  new MongoClient( "localhost" , 27017 );
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		DB db = mongo.getDB(database);
		return db;

		
	}
	
	private ObjectNode constructQuery(String query){
    	ObjectNode filter = OBJECTMAPPER.createObjectNode();
    	ObjectNode oidNode = OBJECTMAPPER.createObjectNode();
    	oidNode.put("$oid", query);
    	filter.put("_id",oidNode);
    	return filter;
    }
    
    
}
