package com.impetus.datafab.valve;

import com.impetus.datafab.core.ValveResponse;
import com.impetus.datastore.QueryExecutor;
import com.impetus.datastore.SolrQueryExecutor;
import com.impetus.datastore.builder.SolrQueryBuilder;

/** The Class SolrValve. */
public class SolrValve implements Valve<ValveResponse> {
	
	@Override
    public String getName() {
        return "Solr Subscriber";
    }
   
    private SolrQueryExecutor solrQueryExecutor;    
    private SolrQueryBuilder solrQB;
    private String dependency;


  
    public SolrQueryBuilder getQueryBuilder() {
		return solrQB;
	}


	public SolrValve() {
        solrQueryExecutor = new SolrQueryExecutor();       
        solrQB = new SolrQueryBuilder();
    }
	
	public SolrValve(String dependency) {
        solrQueryExecutor = new SolrQueryExecutor();       
        solrQB = new SolrQueryBuilder();
        this.dependency=dependency;
    }
   
   
    public QueryExecutor getQueryExecutor() {
        return solrQueryExecutor;
    }
    
    public String getDependency() {
		return dependency;
	}

	public void setDependency(String dependency) {
		this.dependency = dependency;
	}
    
   
}
