package com.impetus.datafab.valve.stream;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.impetus.datafab.core.TubeResponse;
import com.impetus.datafab.core.ValveResponse;
import com.impetus.datafab.valve.Valve;

/**
 * The find template executes find queries based on type of find (e.g. join find, hybrid find).
 *
 */
public interface Find {

    /**
     * This method executes find queries based on type of find operation.
     *
     * @param consistentSubscribers
     *  the consistent subscribers.
     * @param requestMap
     *  the request map.
     * @param bodyJson
     *  the request payload.
     * @param response
     *  the response.
     * @param auditTag
     *  the audit tag.
     * @param ctx TODO
     */
    void executeQuery(List<Valve> consistentSubscribers, JsonNode bodyJson,
            TubeResponse<ValveResponse> response);
}
