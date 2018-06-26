package com.impetus.datafab.valve;

import com.impetus.datastore.MongoQueryExecutor;
import com.impetus.datastore.MongoTransformer;
import com.impetus.datastore.QueryExecutor;
import com.impetus.datastore.Transformer;

/** The Class MongoValve. */
public class MongoValve implements Valve {

   
    /** The mongo query executor. */
    private MongoQueryExecutor mongoQueryExecutor;
    private MongoTransformer mongoTransformer;
    
    
    public MongoValve() {        
        
        mongoQueryExecutor = new MongoQueryExecutor();
        mongoTransformer = new MongoTransformer();
    }

    /*
     * (non-Javadoc)
     * @see com.servicesource.datafab.core.valves.Valve#getName()
     */
   @Override
    public String getName() {
        return "Mongo Subscriber";
    }

  
 
   
    public QueryExecutor getQueryExecutor() {
        return mongoQueryExecutor;
    }

    
  
   
    public Transformer getTransformer() {
        return mongoTransformer;
    }

  
}
