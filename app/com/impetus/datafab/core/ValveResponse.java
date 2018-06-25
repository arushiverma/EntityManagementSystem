package com.impetus.datafab.core;

import java.util.LinkedList;
import java.util.List;
/** Data structure to store response for a single query executed on a Data Store.
 *
 * @param <T>
 *            the generic type */

public class ValveResponse<T> {

    /** The responses. */
    private List<T> responses;

    /** The status. */
    private boolean status;

    /** The error. */
    private String error;

    /** The next cursor mark. **/
    private String nextCursorMark;

    /** The num found. **/
    private long numFound;

    /** The entity to which response is associated. **/
    private String entity;

    /** The has more records. */
    private boolean hasMoreRecords = false;

    /** The valve resposnse code. */
    private int responseCode;

    /** The failed DB Source. */
    private String failedDBSource;
    
  

    /** Instantiates a new task response. */
    public ValveResponse() {
        responses = new LinkedList<T>();
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

    /** Adds the response.
     *
     * @param response
     *            the response to set
     * @param index
     *            the index */
    public void addResponse(T response, int index) {
        this.responses.add(index, response);
    }

    /** @param response
     *            the response
     * @param index
     *            the index */
    public void replaceResponse(T response, int index) {
        this.responses.remove(index);
        this.responses.add(index, response);
    }

    /** Adds the all responses.
     *
     * @param moreResponses
     *            the more responses */
    public void addAllResponses(List<T> moreResponses) {
        this.responses.addAll(moreResponses);
    }

    /** Gets the status.
     *
     * @return the status */
    public boolean getStatus() {
        return status;
    }

    /** Sets the status.
     *
     * @param status
     *            the new status */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /** Get Failed DB Source.
     *
     * @return the failed db source */
    public String getFailedDBSource() {
        return failedDBSource;
    }

    /** Set DB Source.
     *
     * @param failedDBSource
     *            the failed db source. */
    public void setFailedDBSource(String failedDBSource) {
        this.failedDBSource = failedDBSource;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TaskResult [responses=" + responses + ", status=" + status + ", nextCursorMark=" +nextCursorMark + ", numFound=" +numFound +"]";
    }

    /** Sets the error.
     *
     * @param error
     *            the new error */
    public void setError(String error) {
        this.error = error;
    }

    /** Gets the error.
     *
     * @return the error */
    public String getError() {
        return error;
    }

    /**
     * @return the nextCursorMark
     */
    public String getNextCursorMark() {
        return nextCursorMark;
    }

    /**
     * @param nextCursorMark the nextCursorMark to set
     */
    public void setNextCursorMark(String nextCursorMark) {
        this.nextCursorMark = nextCursorMark;
    }

    /**
     * @return the numFound
     */
    public long getNumFound() {
        return numFound;
    }

    /**
     * @param numFound the numFound to set
     */
    public void setNumFound(long numFound) {
        this.numFound = numFound;
    }

    /**
     * @return the entity
     */
    public String getEntity() {
        return entity;
    }

    /**
     * @param entity the entity to set
     */
    public void setEntity(String entity) {
        this.entity = entity;
    }

    /**
     * @return the hasMoreRecords
     */
    public boolean isHasMoreRecords() {
        return hasMoreRecords;
    }

    /**
     * @param hasMoreRecords the hasMoreRecords to set
     */
    public void setHasMoreRecords(boolean hasMoreRecords) {
        this.hasMoreRecords = hasMoreRecords;
    }

    /** Getter Response Code..
     *
     * @return */
    public int getResponseCode() {
        return responseCode;
    }

    /** Setter Response Code.
     *
     * @param responseCode */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
    
    /** Clears all responses from Object. */
    public void removeAll() {
        if (responses != null && !responses.isEmpty()) {
            responses.clear();
        }
    }

 
}
