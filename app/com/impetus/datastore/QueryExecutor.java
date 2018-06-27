package com.impetus.datastore;

import com.impetus.datafab.core.ValveResponse;

public interface QueryExecutor {

	ValveResponse executeQuery(String operation, String query, String entity);

}
