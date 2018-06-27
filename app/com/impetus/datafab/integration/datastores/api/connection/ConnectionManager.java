package com.impetus.datafab.integration.datastores.api.connection;

/** Strategy interface to provide connection management mechanism Implementation of this class are expected to initialize a pool at the start of.
 * application. */
public interface ConnectionManager {

  
    //Connection borrowConnection(QueryContext queryContext, Context context);

    /** Implementations must return the instance back to the Connection Manager instance. */
    void releaseConnection();

}
