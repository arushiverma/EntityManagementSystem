package com.impetus.datastore;


import com.fasterxml.jackson.databind.node.ObjectNode;

public interface Transformer {

 
    void transformRequest(ObjectNode linkedRefNode);

    
   /* ValveResponse<?> transformResponse(ValveResponse<?> taskResult, Connection connection, QueryContext queryContext, Query query, JsonNode origDoc,
            ObjectNode linkReferenceNode, Context context);*/
}
