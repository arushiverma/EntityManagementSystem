package com.impetus.datafab.valve;

/** The Class SolrValve. */
public class SolrValve implements Valve {
	
	@Override
    public String getName() {
        return "Solr Subscriber";
    }

	/*


    *//** The solr query executor. *//*
    private SolrQueryExecutor solrQueryExecutor;    

    *//** Solr transformer **//*
    private SolrTransformer solrTransformer;

   

    *//** Instantiates a new solr valve.
     *
     * @param dataFlowTask
     *            the data flow task *//*
    public SolrValve() {
        solrQueryExecutor = new SolrQueryExecutor();       
        solrTransformer = new SolrTransformer();
    }

    
     * (non-Javadoc)
     * @see com.servicesource.datafab.core.valves.Valve#getName()
     
    @Override
    public String getName() {
        return "Solr Subscriber";
    }

    
     * (non-Javadoc)
     * @see com.servicesource.datafab.core.valves.PersistentValve#getQueryBuilder()
     
   
   
    protected QueryExecutor getQueryExecutor() {
        return solrQueryExecutor;
    }

    

     (non-Javadoc)
     * @see com.servicesource.datafab.core.valves.PersistentValve#getTransformer()
     
    
    protected Transformer getTransformer(){
        return solrTransformer;
    }

*/}
