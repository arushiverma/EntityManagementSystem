package com.impetus.datafab.valve.stream;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.impetus.datafab.core.TubeResponse;
import com.impetus.datafab.core.ValveResponse;
import com.impetus.datafab.valve.Valve;
import com.impetus.datastore.MongoQueryExecutor;
import com.impetus.datastore.QueryExecutor;

public class SimpleFind implements Find {

	@Override
	public void executeQuery(List<Valve> subscribers, JsonNode bodyJson,
			TubeResponse<ValveResponse> response) {
		// call transformer and executor here
		for (Valve subscriber : subscribers) {
            System.out.println("Syncing State with: " + subscriber.getName());
            QueryExecutor queryExec = new MongoQueryExecutor();
            queryExec.executeQuery("FIND", "5b30d2b359aa6436facc60c9", "app.products");
            //response.addResponse(result);
        }
	}

}
