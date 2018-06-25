/**
 *
 */
package com.impetus.datafab.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.Config;

/**
 *  This is the contract for sending requests persistent subscribers.
 */
public interface Tube {

    /**
     * Pour incoming data to tube so it can be subscribers.
     *
     *
     */
    TubeResponse<ValveResponse> pourData(String objectType, String operation, JsonNode bodyJson, Config conf);

   
}
