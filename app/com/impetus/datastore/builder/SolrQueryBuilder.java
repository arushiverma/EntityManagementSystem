package com.impetus.datastore.builder;

import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.impetus.datafab.core.ValveResponse;

public class SolrQueryBuilder implements QueryBuilder {

	@Override
	public String constructQuery(JsonNode node, String operation, ValveResponse prevResp) {
		// TODO Auto-generated method stub
		//for find only
		
		
		StringBuilder querystring = new StringBuilder();
		JsonNode filter=node.get("filter");
			
		Iterator<String> fields =filter.fieldNames();
        String field=null;
        
        int count = 1;
        while(fields.hasNext()){
            field=fields.next();
            querystring = querystring.append(field+":"+"\""+ filter.get(field).asText() +"\"");
            
            querystring = querystring.append("AND");
        }
        int idx=querystring.lastIndexOf("AND");
        querystring = querystring.delete(idx, querystring.length());
		return querystring.toString();
	}

}
