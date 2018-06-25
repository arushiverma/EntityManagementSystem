/*package com.impetus.datafab.core;

import java.util.List;
import java.util.Map;

import play.Logger.ALogger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.servicesource.commons.NumberConstants;
import com.servicesource.ctx.Context;
import com.servicesource.ctx.ContextHelper;
import com.servicesource.datafab.core.tube.BaseTube;
import com.servicesource.datafab.core.valves.EventValve;
import com.servicesource.datafab.core.valves.PersistentValve;
import com.servicesource.datafab.integration.datastores.api.query.models.OperationType;
import com.servicesource.datafab.utils.DataFabricConstants;
import com.servicesource.datafab.utils.ExceptionConstants;
import com.servicesource.datafab.utils.JsonUtil;
import com.servicesource.datafab.utils.Literals;
import com.servicesource.datafab.utils.RetryTransactionUtil;
import com.servicesource.datafab.utils.exceptions.TaskExecutionException;
import com.servicesource.util.LogUtils;

*//** This is responsible for sending the data to consistent & eventually consistent subscribers. *//*
@SuppressWarnings("rawtypes")
public class SyncTube extends BaseTube {

    *//** The log. *//*
    private static ALogger log = LogUtils.getLogger(SyncTube.class);

    *//** Synchronizing to Consistent Subscribers. This is a high priority operation, requires to happen successfully otherwise we call off the sync
     * operation.
     *
     * @param consistentSubscribers
     *            the consistent subscribers
     * @param requestMap
     *            the request map
     * @param bodyJson
     *            the body json
     * @param response
     *            the response
     * @param context
     *            the context *//*
    @Override
    protected void streamToPersistentValves(List<PersistentValve> consistentSubscribers, Map<String, Object> requestMap, JsonNode bodyJson,
            TubeResponse<ValveResponse> response, Context context) {
        response.setStatus(TubeStatus.PENDING);
        Context ctx=ContextHelper.getContext();
        try {
            for (PersistentValve subscriber : consistentSubscribers) {
                LogUtils.info(log, "Syncing State with: " + subscriber.getName(), ctx.getAuditTag());
                ValveResponse result = subscriber.flowData(ctx,requestMap, bodyJson, response);
                response.addResponse(result);
            }
            response.setStatus(TubeStatus.CONSISTENT_SUCCEED);
        } catch (Exception exception) {
            prepareErrorResponse(response, ctx, exception);
        }
    }

    *//** Synchronizing to eventually consistent subscribers, like SFDC, External Services. Expected to be asychronous syncing.
     * @param requestMap
     *            the request map
     * @param bodyJson
     *            the body json
     * @param response
     *            the response
     * @param eventuallyConsistentSubscribersMap
     *            the eventually consistent subscribers
     * @param context
     *            the context
     * @return the tube response *//*
    @Override
    public TubeResponse<ValveResponse> streamToEventValves(Map<EventValve, List<Integer>> eventuallyConsistentSubscribersMap, Map<String, Object> requestMap,
            JsonNode bodyJson, TubeResponse<ValveResponse> response, Context context) {
        response.setStatus(TubeStatus.EVENTUALLY_CONSISTENT_PENDING);
        JsonNode responseJson = null;
        String auditTag = context.getAuditTag();
        try {
            if(eventuallyConsistentSubscribersMap != null) {
                LogUtils.info(log, "Total eventual subscribers " + eventuallyConsistentSubscribersMap.size(), auditTag);
                for (Map.Entry<EventValve, List<Integer>> eventuallyConsistentSubscribersEntry : eventuallyConsistentSubscribersMap.entrySet()) {
                    EventValve subscriber = eventuallyConsistentSubscribersEntry.getKey();
                    List<Integer> indexList = eventuallyConsistentSubscribersEntry.getValue();
                    LogUtils.info(log, "Syncing State with: " + subscriber.getName(), auditTag);
                    if (OperationType.SOFT_DELETE.getOperation().equals(context.getRequestContext().getOperation())){
                        if (response.getResponses().size() > Literals.ZERO) {
                            responseJson = JsonUtil.getAsJSONNode(response.getLastResponse().getResponses().toString());
                        }
                    } else {
                        if (response.getFirstResponse() != null && response.getFirstResponse().getResponses().size() > 0) {
                            responseJson = JsonUtil.getAsJSONNode(response.getFirstResponse().getResponses().toString());
                        } else {
                            ArrayNode node = JsonUtil.getArrayNodeInstance();
                            node.add(bodyJson);
                            responseJson = JsonUtil.getAsJSONNode(node.toString());
                        }
                        if (indexList != null && indexList.size() > NumberConstants.ZERO) {
                            requestMap.put(DataFabricConstants.INDEX_LIST, indexList);
                        }
                    }
                    response.addResponse(subscriber.flowEvent(context, requestMap, responseJson));
                }
            }
            response.setStatus(TubeStatus.CONSISTENT_SUCCEED);
            return response;
        } catch (Exception e) {
            LogUtils.error(log, response.getStatus().toString(),auditTag, e);
            response.setStatus(TubeStatus.CONSISTENT_FAILED);
            return response;
        }
    }

    *//** This Method will create the error response If tarnsaction fails for create,update or retryTransaction.
     *
     * @param response the response
     * @param ctx
     *            context
     * @param exception
     *            exception *//*
    protected void prepareErrorResponse(TubeResponse<ValveResponse> response, Context ctx, Exception exception) {
        String operation = ctx.getRequestContext().getOperation();
        String failedDbSource = null;
        if (exception instanceof TaskExecutionException && ((TaskExecutionException) exception).getFailedDBSource() != null) {
            failedDbSource = ((TaskExecutionException) exception).getFailedDBSource();
        }

        if (!RetryTransactionUtil.isDLQRetryHandler(failedDbSource, operation)) {
            LogUtils.error(log, response.getStatus().toString(), ctx.getAuditTag(), exception);
        }
        ValveResponse errorValveRes = new ValveResponse();
        errorValveRes.setError(exception.getMessage());
        errorValveRes.setResponseCode(ExceptionConstants.ERROR_VALVE_RESPONSE_CODE);
        *//** Updating Failed Source in Error Valve Responce. *//*
        if (failedDbSource != null) {
            errorValveRes.setFailedDBSource(failedDbSource);
        }
        response.setStatus(TubeStatus.CONSISTENT_FAILED);
        response.addResponse(errorValveRes);
    }
}

*/