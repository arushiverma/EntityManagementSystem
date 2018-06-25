package com.impetus.datafab.core;

import java.util.LinkedList;
import java.util.List;

//TODO Auto-generated Javadoc
/** The Class TubeResponse.
 *
 * @param <T>
 *            the generic type */
@SuppressWarnings("rawtypes")
public class TubeResponse<T extends ValveResponse> {

    /** The status. */
    private TubeStatus status;

    /** The responses. */
    private List<T> responses;

    /** Instantiates a new tube response. */
    public TubeResponse() {
        responses = new LinkedList<T>();
    }

    /** Gets the status.
     *
     * @return the status */
    public TubeStatus getStatus() {
        return status;
    }

    /** Sets the status.
     *
     * @param status
     *            the status to set */
    public void setStatus(TubeStatus status) {
        this.status = status;
    }

    /** Gets the responses.
     *
     * @return the response */
    public List<T> getResponses() {
        return responses;
    }

    /** Adds the response.
     *
     * @param response
     *            the response to set */
    public void addResponse(T response) {
        this.responses.add(response);
    }

    /** Adds the all responses.
     *
     * @param moreResponses
     *            the more responses */
    public void addAllResponses(List<T> moreResponses) {
        this.responses.addAll(moreResponses);
    }

    /** Gets the last response.
     *
     * @return the result of last executed Task */
    public ValveResponse getLastResponse() {
        ValveResponse lastTaskResult = null;
        int totalResults = responses.size();
        if (totalResults > 0) {
            lastTaskResult = responses.get(totalResults - 1);
        }
        return lastTaskResult;
    }

    /** Gets the first response.
     *
     * @return the result of last executed Task */
    public ValveResponse getFirstResponse() {
        ValveResponse firstTaskResult = null;
        if (responses != null && responses.size() > 0) {
            firstTaskResult = responses.get(0);
        }
        return firstTaskResult;
    }

    /** Clears all responses from Object. */
    public void removeAll() {
        if (responses != null && !responses.isEmpty()) {
            responses.clear();
        }
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BroadcastResponse [status=" + status + ", responses=" + responses + "]";
    }

}
