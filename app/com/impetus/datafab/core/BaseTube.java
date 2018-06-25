package com.impetus.datafab.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.servicesource.datafab.core.valves.DataStoreValvesEnum;
import com.servicesource.datafab.core.valves.MongoValve;
import com.servicesource.datafab.core.valves.SolrValve;
import com.typesafe.config.Config;



public class BaseTube implements Tube {
	
	 /** The conf. */
    //private static play.Configuration conf = Play.application().configuration();	
	
	
	public TubeResponse<ValveResponse> pourData(String entity, String operation,JsonNode bodyJson, Config conf) {        
       
		 List<String> methodFlowKeys = new ArrayList<String>();
		 methodFlowKeys.add(operation + "."+ entity );
	     methodFlowKeys.add(operation);
	     FlowDetails flows = fetchDataflow(operation, conf, methodFlowKeys);
	     System.out.println(flows);
        //iterate on flows -- check store-- call its trabsformer and executor and accumulate responses.
//	     /List<? extends DataFlowTask> tasks = dataFlow.getTasks();
	   /*  if (dataStore.equals("mongo")) {
	            return new MongoValve(task);
	        } else if ("solr") {
	            return new SolrValve(task);
	        }*/
        return null;
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

}
