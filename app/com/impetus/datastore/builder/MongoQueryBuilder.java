package com.impetus.datastore.builder;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.impetus.datafab.core.ValveResponse;
import com.impetus.entity.constants.EntityConstants;


public class MongoQueryBuilder implements QueryBuilder {
	
	private static ObjectMapper MAPPER=EntityConstants.OBJECTMAPPER;

	@Override
	public String constructQuery(JsonNode node, String operation, ValveResponse prevResp) {
		// TODO Auto-generated method stub
		JsonNode req = null;
		switch(operation) {
		case "findOne":
			List<String> ids = prevResp.getResponses();
			return prepareFilterForInQuery(ids).toString();		
		case "find":
			req=node.get("filter");
			if(req.has(EntityConstants.ID)) {
				return constructIdQuery(req.get(EntityConstants.ID).asText()).toString();
			}
			break;
		
		}
		
		return req.toString();
	}
	
	
	private ObjectNode getOidNode(String query){
    	ObjectNode oidNode = EntityConstants.OBJECTMAPPER.createObjectNode();
    	oidNode.put("$oid", query);
    	return oidNode;
    }
	
	
	/** Prepare filter node for IN query.
    *
    * @param ids
    *            the ids
    * @return the object node */
   private ObjectNode prepareFilterForInQuery(List<String> ids) {
       ObjectNode filter = MAPPER.createObjectNode();
       ArrayNode oidArray = MAPPER.createArrayNode();
       ObjectNode inQueryNode = MAPPER.createObjectNode();

       for (Object id : ids) {
           ObjectNode oidNode = null;
           if (id instanceof ObjectNode) {
               oidNode = getOidNode(((ObjectNode) id).get(EntityConstants.ID).asText());

           } else {
               oidNode = getOidNode(id.toString());
           }
           if (!oidNode.isNull()) {
               oidArray.add(oidNode);
           }

       }
       inQueryNode.put("$in", oidArray);
       filter.put(EntityConstants.ID, inQueryNode);
       return filter;
   }
   
   private ObjectNode constructIdQuery(String query){
   	ObjectNode filter = MAPPER.createObjectNode();
   	filter.put("_id",getOidNode(query));
   	return filter;
   }

}
