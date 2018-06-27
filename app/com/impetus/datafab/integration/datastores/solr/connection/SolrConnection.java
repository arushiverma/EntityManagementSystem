//package com.impetus.datafab.integration.datastores.solr.connection;
//
//import java.util.Map;
//
//import org.apache.solr.client.solrj.SolrClient;
//import org.apache.solr.client.solrj.impl.CloudSolrClient;
//
//import com.impetus.datafab.integration.datastores.api.connection.Connection;
//
//
///** Stores reference of Solr connection. */
//public class SolrConnection implements Connection {
//
//    /** The server. */
//    private CloudSolrClient server;
//    //final SolrClient client = getSolrClient();
//
//    String zkServerURI = "zkServerA:9983,zkServerB:9983,zkServerC:9983/solr";
//
//    /**Instantiates a new solr connection.
//     * @param zkServerURI
//     *          zkServer URI
//     * @param solrConnProperties
//     *          Solr ConnProperties
//     */
//    public SolrConnection(Map<String, Object> solrConnProperties) {
//        if (server == null) {}
//    }
//
//    /** Gets the solr server.
//     *
//     * @return the solr server */
//    public CloudSolrClient getSolrServer() {
//        return server;
//    }
//
//    /** Close connection. */
//    public void closeConnection() {
//        // do nothing
//        // automatically manages connection pooling
//        closeConnectionToServer();
//        LogUtils.info(log, SolrConnection.class + " - Releasing Solr connection.", null);
//    }
//
//    /** Close connection to server. */
//    public void closeConnectionToServer() {
//        if (server != null) {
//            server.shutdown();
//        }
//    }
//
//    /*
//     * @see java.lang.Object#clone()
//     */
//    /*
//     * (non-Javadoc)
//     * @see java.lang.Object#clone()
//     */
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        throw new CloneNotSupportedException();
//    }
//}
