package com.impetus.datafab.valve;

import com.impetus.datastore.MongoQueryExecutor;
import com.impetus.datastore.MongoTransformer;
import com.impetus.datastore.QueryExecutor;
import com.impetus.datastore.Transformer;
import com.impetus.datastore.builder.MongoQueryBuilder;
import com.impetus.datastore.builder.QueryBuilder;

/** The Class MongoValve. */
public class MongoValve implements Valve {

   
    /** The mongo query executor. */
    private MongoQueryExecutor mongoQueryExecutor;
    private MongoTransformer mongoTransformer;
    private MongoQueryBuilder mongoQueryBuilder;
    private String dependency;
    
    
    public MongoValve() {        
        
        mongoQueryExecutor = new MongoQueryExecutor();
        mongoTransformer = new MongoTransformer();
        mongoQueryBuilder=new MongoQueryBuilder();
    }
    
    
    public MongoValve(String dependency) {        
        
        mongoQueryExecutor = new MongoQueryExecutor();
        mongoTransformer = new MongoTransformer();
        mongoQueryBuilder=new MongoQueryBuilder();
        this.dependency=dependency;
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
    
    public QueryBuilder getQueryBuilder() {
    	return mongoQueryBuilder;
    }

	public String getDependency() {
		return dependency;
	}

	public void setDependency(String dependency) {
		this.dependency = dependency;
	}
    
  
}
