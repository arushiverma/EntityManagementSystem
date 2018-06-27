/*package com.impetus.datafab.integration.datastores.solr.connection;

import java.util.HashMap;
import java.util.Map;

import play.Logger.ALogger;

import com.impetus.datafab.integration.datastores.api.connection.Connection;
import com.impetus.datafab.integration.datastores.api.connection.ConnectionManager;
import com.servicesource.ctx.Context;
import com.servicesource.datafab.integration.datastores.api.query.QueryContext;
import com.servicesource.datafab.integration.datastores.api.query.models.OperationType;
import com.servicesource.datafab.integration.datastores.cluster.Shard;
import com.servicesource.datafab.utils.CollectionConfigurationUtil;
import com.servicesource.datafab.utils.Literals;
import com.servicesource.util.LogUtils;
import com.servicesource.util.SolrConfigurationUtil;

*//** The Class SolrConnectionManager. *//*
public final class SolrConnectionManager implements ConnectionManager {

    *//** The log. *//*
    private static ALogger log = LogUtils.getLogger(SolrConnectionManager.class);

    *//** The solr connection. *//*
    //private SolrConnection solrConnection;

    *//** The solr http connection map. *//*
    private static final Map<String, SolrHttpConnection> SOLR_HTTP_CONNECTION_MAP = new HashMap<String, SolrHttpConnection>();;

    *//** Connection manager. *//*
    private static SolrConnectionManager connectionManager;

    *//** The solr connection. *//*
    private Map<String, SolrConnection> solrTenantConnection = new HashMap<String, SolrConnection>();

    *//** Instantiates a new solr connection manager. *//*
    private SolrConnectionManager() {

    }

    *//** Gets the single instance of SolrConnectionManager.
     *
     * @return single instance of SolrConnectionManager *//*
    public static SolrConnectionManager getInstance() {
        if (connectionManager == null) {
            synchronized (SolrConnectionManager.class) {
                if (connectionManager == null) {
                    connectionManager = new SolrConnectionManager();
                }
            }
        }
        return connectionManager;
    }

    
     * (non-Javadoc)
     * @see com.servicesource.datafab.integration.datastores.api.connection.ConnectionManager#borrowConnection()
     
    @Override
    public Connection borrowConnection(QueryContext queryContext, Context context) {
        String auditTag = null;
        if(context != null){
            auditTag = context.getAuditTag();
        }
        if (queryContext == null || queryContext.getTenant() == null || queryContext.getEntity() == null) {
            LogUtils.warn(log, "Query Context or tenant name is null " + queryContext + " tenant " + queryContext.getTenant() + " for collection "
                    + queryContext.getEntity(), auditTag);
        }
        final OperationType operationType = OperationType.getOperationType(queryContext.getOperation());
        if (operationType != null) {
            switch (operationType) {
                case FIND_BY_JOIN:
                case COUNT_BY_JOIN:
                case AGGREGATE_BY_JOIN:
                    final Shard shard = queryContext.getShard();
                    String host = shard.getHost();
                    final Map<String, String> collections = shard.getCollections();
                    host = host + Literals.PATH_SEPARATOR + CollectionConfigurationUtil.getTransformedShardName(context, queryContext.getEntity(), collections.get(queryContext.getEntity()));
                    return getHttpConnection(host);
                default:
                    break;
            }
        }
        LogUtils.info(log, SolrConnection.class + " - Acquiring Solr connection from pool for tenant : "+queryContext.getTenant() +" Collection "+queryContext.getEntity(), auditTag);

        if (queryContext != null && queryContext.getTenant() != null) {
            final String groupName = SolrConfigurationUtil.getGroupKey(context, SolrConfigurationUtil.getZKServerGroupName(context, queryContext.getTenant(), queryContext.getEntity()));
            if (solrTenantConnection.get(groupName) == null) {
                synchronized (SolrConnectionManager.class) {
                    if (solrTenantConnection.get(groupName) == null) {
                        final Map<String, Object> solrConnProperties = SolrConfigurationUtil.getSolrConnectionProperties(context,
                                queryContext.getTenant(), queryContext.getEntity());
                        final String zkServerURI = SolrConfigurationUtil.getSolrZKServer(context, queryContext.getTenant(), queryContext.getEntity());
                        final SolrConnection solrConn = new SolrConnection(zkServerURI, solrConnProperties);
                        solrTenantConnection.put(groupName, solrConn);
                        return solrConn;
                    }
                }
            }
            LogUtils.info(log, "******************Successfully established Solr Connection cluster : " + groupName + " for tenant : " + queryContext.getTenant() + " collection : " + queryContext.getEntity(), auditTag);
            return solrTenantConnection.get(groupName);
        }
        return null;
    }

    *//**
     * Gets the http connection.
     *
     * @param host      the host
     * @return SolrHttp Connection
     *//*
    public Connection getHttpConnection(final String host) {
        if (SOLR_HTTP_CONNECTION_MAP.get(host) == null) {
            synchronized (SolrConnectionManager.class) {
                if (SOLR_HTTP_CONNECTION_MAP.get(host) == null) {
                    SolrHttpConnection solrHttpConnection = new SolrHttpConnection(host);
                    SOLR_HTTP_CONNECTION_MAP.put(host, solrHttpConnection);
                    return solrHttpConnection;
                }
            }
        }
        return SOLR_HTTP_CONNECTION_MAP.get(host);
    }

    
     * (non-Javadoc)
     * @see com.servicesource.datafab.integration.datastores.api.connection.ConnectionManager#releaseConnection()
     
    @Override
    public void releaseConnection() {
        if (solrTenantConnection != null && !solrTenantConnection.isEmpty()) {
            for(final SolrConnection solrConn : solrTenantConnection.values()) {
                if(solrConn!=null) {
                    solrConn.closeConnection();
                }
            }
        }
        if (SOLR_HTTP_CONNECTION_MAP != null) {
            for (SolrHttpConnection solrHttpConnection : SOLR_HTTP_CONNECTION_MAP.values()) {
                if (solrHttpConnection != null) {
                    solrHttpConnection.closeConnection();
                }
            }
        }
    }

}
*/