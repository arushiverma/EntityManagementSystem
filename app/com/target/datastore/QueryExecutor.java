package com.target.datastore;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface QueryExecutor {

	ObjectNode executeQuery(String operation, String query, String entity);

}
