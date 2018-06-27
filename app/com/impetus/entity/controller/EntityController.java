package com.impetus.entity.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.impetus.entity.businessservice.BaseBusinessService;
import com.typesafe.config.Config;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Result;

public class EntityController extends Controller{

	BaseBusinessService ebs = new BaseBusinessService();
	/** The object mapper. **/
    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
    private final Config config;

    @Inject
    public EntityController(Config config) {
        this.config = config;
    }
	
	/*public Result find(String entity,String id) {
		
		ObjectNode node = OBJECTMAPPER.createObjectNode();
		node.put("filter", )
	}*/
	
	public Result find(String entity) {
		Object finalResponse = null;
		JsonNode reqBody = request().body().asJson();
		try {
			//prepareRequestMap("find",entity);
			finalResponse = ebs.find(reqBody, entity,config);

			if (finalResponse != null) {
				System.out.println(finalResponse.toString());
				return ok(finalResponse.toString());
			}
			} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status(BAD_GATEWAY, "Request not processed");
	}

	public Result update(String entity,String id) {
		System.out.println("Received update request for "+entity);
		JsonNode reqBody = request().body().asJson();
		 Object finalResponse = null;
		 
		 try {
			finalResponse = ebs.update(reqBody);
			 if (finalResponse != null) {
					return ok(finalResponse.toString());
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return status(INTERNAL_SERVER_ERROR, "Failed to process request");
		}
		
		 return status(INTERNAL_SERVER_ERROR, "Failed to process request");
	}

	public Result delete(String entity,String id) {
		System.out.println("Received delete request for "+entity);
		 Object finalResponse = null;
		 try {
			finalResponse = ebs.delete(id);
			 if (finalResponse != null) {
					return ok(finalResponse.toString());
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return status(INTERNAL_SERVER_ERROR, "Failed to process request");
		}
		
		 return status(INTERNAL_SERVER_ERROR, "Failed to process request");
	}

	public Result create(String entity) {
		System.out.println("Received create request for "+entity);
		JsonNode reqBody = request().body().asJson();
		 Object finalResponse = null;
		 try {
			finalResponse = ebs.create(reqBody);
			 if (finalResponse != null) {
					return ok(finalResponse.toString());
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return status(INTERNAL_SERVER_ERROR, "Failed to process request");
		}
		
		 return status(INTERNAL_SERVER_ERROR, "Failed to process request");
	}

	/** Retrieves the context from Http request.
	 *
	 * @return {@link Context} */
	public static Object getContext() {
		Object ctx =  Http.Context.current().args.get("context");

		return ctx;
	}
	
	public static Map<String,String> prepareRequestMap(String operation, String entity) {
		Map<String, String> reqMap = new HashMap<>();
		reqMap.put("operation", operation);
		reqMap.put("entity", entity);
		return reqMap;
	}

}
