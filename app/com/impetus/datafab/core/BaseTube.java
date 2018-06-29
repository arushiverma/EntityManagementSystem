package com.impetus.datafab.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.impetus.datafab.core.tube.factory.OperationType;
import com.impetus.datafab.valve.MongoValve;
import com.impetus.datafab.valve.SolrValve;
import com.impetus.datafab.valve.Valve;
import com.impetus.datastore.utils.QueryUtil;
import com.typesafe.config.Config;



public abstract class BaseTube implements Tube {
	
	
	public TubeResponse<ValveResponse> pourData(String entity, String operation,JsonNode bodyJson, Config conf) {        
		 boolean isFindOne = QueryUtil.isFindOne(bodyJson);
		 operation = isFindOne ? OperationType.FIND_ONE.getOperation():operation;
		 List<String> methodFlowKeys = new ArrayList<String>();
		 methodFlowKeys.add(operation + "-"+ entity );
	     methodFlowKeys.add(operation);
	     FlowDetails flows = fetchDataflow(operation, conf, methodFlowKeys);
	     System.out.println(flows);
	     Map<Integer, StoreTuple> datastores  = flows.getDataflowTuples();
       
	     List<Valve> subscribers=getSubscribers(datastores);
	     TubeResponse<ValveResponse> response = new TubeResponse<ValveResponse>();
	     streamToPersistentValves(subscribers, bodyJson, response, entity, conf); // for multithreading response is being sent in argument
	    	 
	     
        return response;
    }

	private List<Valve> getSubscribers(Map<Integer, StoreTuple> datastores) {
		List<Valve> valves = new LinkedList<>();
		for(StoreTuple store : datastores.values()) {
	    	 String storename= store.getStore();
	    	 String queryDependency = store.getDependencies().size()>0?store.getDependencies().get(0):null;
	    	 
	    	 switch(storename) {
	    		 case "mongodb":
	    			 valves.add(new MongoValve(queryDependency));
	    		 	break;
	    		 case "solr":
	    			 valves.add(new SolrValve());
	    		 	break;
	    	 }
	     }
		return valves;
	}

	private FlowDetails fetchDataflow(String operation, Config conf, List<String> methodFlowKeys) {
		Config dataflowconf=conf.getConfig("dataflow-definitions");
	     FlowDetails flowDetails = new FlowDetails();
	     for (String methodFlowKey : methodFlowKeys) {
	    	 if(dataflowconf.hasPath(methodFlowKey)) {
	    		 
	    		 Map<String, Object> flowDetailsInternalKey = (Map<String, Object>) dataflowconf.getAnyRef(methodFlowKey);	                

	                Object flowDetailsValue = flowDetailsInternalKey.get("data-flow");
	                for (Map.Entry<String, Object> storeTupleEntry : ((Map<String, Object>) flowDetailsValue).entrySet()) {
	                    StoreTuple storeTuple = new StoreTuple();
	                    String identifierKey = storeTupleEntry.getKey();
	                    Map<String, Object> storeTupleValue = (Map<String, Object>) storeTupleEntry.getValue();
	                    storeTuple.setStore((String) storeTupleValue.get("store"));
	                    storeTuple.setDependencies((List<String>) storeTupleValue.get("dependency"));
	                    storeTuple.setOperation((String) storeTupleValue.get(operation));
	                    flowDetails.addToDataflowTuples(Integer.parseInt(identifierKey), storeTuple);
	                }
	                break;
	    		 
	    		 
	    	 }
	     }
	     return flowDetails;
	}
	
	protected abstract void streamToPersistentValves(List<Valve> consistentSubscribers,JsonNode bodyJson,
            TubeResponse<ValveResponse> response, String entity, Config conf);
	
	

}
