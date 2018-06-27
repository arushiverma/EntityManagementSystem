package com.impetus.datafab.integration.datastores.solr.connection;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import com.impetus.datafab.integration.datastores.api.connection.Connection;

/**
 * The Class SolrHttpConnection.
 */
public class SolrHttpConnection implements Connection {

    /** The server. */
    private SolrClient server;

   

    /**
     *  Instantiates a new solr connection.
     *
     * @param host the host
     */
    public SolrHttpConnection(String host) {
        //LogUtils.info(log, SolrHttpConnection.class + " - Acquiring Http Solr connection from pool. " + host, null);
        //server = new HttpSolrClient(host);
    }

    /** Gets the solr server.
     *
     * @return the solr server */
    public SolrClient getSolrServer() {
        return server;
    }

    /** Close connection. */
    public void closeConnection() {
        // do nothing
        // automatically manages connection pooling
        closeConnectionToServer();
        //LogUtils.info(log, SolrConnection.class + " - Releasing Solr connection.", null);
    }

    /** Close connection to server. */
    public void closeConnectionToServer() {
        if (server != null) {
            //server.shutdown();
        }
    }

    /*
     * @see java.lang.Object#clone()
     */
    /*
     * (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
