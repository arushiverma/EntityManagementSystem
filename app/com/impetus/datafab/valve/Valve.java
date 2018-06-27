package com.impetus.datafab.valve;

import com.impetus.datastore.QueryExecutor;
import com.impetus.datastore.builder.QueryBuilder;

/** The base of all consistent & evetually consistent subscribers.
 * 
 * @param <ResponseType>
 *            the generic type */
public interface Valve<ResponseType> {

    /** Gets the name.
     * 
     * @return the name */
    String getName();    
   
    QueryExecutor getQueryExecutor();
    
    QueryBuilder getQueryBuilder();
    
    String getDependency() ;

	void setDependency(String dependency);	
    
    

}