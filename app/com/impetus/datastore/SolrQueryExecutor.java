package com.impetus.datastore;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.impetus.datafab.core.ValveResponse;
import com.impetus.entity.constants.EntityConstants;

/** Responsible for executing queries on Solr Server. */


public class SolrQueryExecutor implements QueryExecutor {
	String zkHosts ="172.25.46.76:9983";
	
	private CloudSolrClient solrClient = new CloudSolrClient(zkHosts,true);
	private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
	

	@Override
	public ValveResponse executeQuery(String operation, String query, String entity) {	
		
		ValveResponse result=null;
		if (operation != null) {
            switch (operation) {
                case "find":
                	result = executeFindQuery(query, entity);                	
                    break;
              /*  case "UPDATE":
                	result = executeUpdateQuery(query, entity);                	
                    break;
                case "CREATE":
                	result = executeCreateQuery(query, entity);
                	break;
                case "DELETE":
                	result = executeDeleteQuery(query, entity);
                	break;*/
                default:
                    break;
                    }
            }
		return result;
	}

	private ValveResponse executeFindQuery(String query,String entity) {
		
		final String collectionName = entity;//TODO
		System.out.println("Solr Query : " +query);
		QueryResponse response=null;
		ArrayNode columns = OBJECTMAPPER.createArrayNode();
		columns.add("name");
		try {
			//doc = solrClient.getById(collectionName, id);
			final SolrQuery querys = new SolrQuery(query);
			querys.addField(EntityConstants.ID);
			querys.addField("name");
			response = solrClient.query(collectionName, querys);
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SolrDocumentList docsList = response.getResults();
		ValveResponse res = new com.impetus.datafab.core.ValveResponse();
		res.setNumFound(docsList.getNumFound());
		for (SolrDocument doc : docsList) {
            /** If columns are present in query then retrive them, create list of ObjectNode and send the output, otherwise create list of IDs. */
            if (columns.isArray() && columns.size() >1) {
                ObjectNode resultNode = OBJECTMAPPER.createObjectNode();
                for (JsonNode col : columns) {
                    if (doc.get(col.asText()) != null) {
                        resultNode.put(col.asText(), doc.get(col.asText()).toString());
                    }
                }
                res.addResponse(resultNode);
            }
		else {
            	res.addResponse(doc.get(EntityConstants.ID));
            }
        }
		return res;
	}
	
	/** Close connection. */
    public void closeConnection() {
        // do nothing
        // automatically manages connection pooling
        closeConnectionToServer();
    }

    /** Close connection to server. */
    public void closeConnectionToServer() {
        if (solrClient != null) {
        	try {
				solrClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
        }
    }

	
	
}
