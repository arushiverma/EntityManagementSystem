package com.impetus.datafab.core;

//TODO Auto-generated Javadoc
/** The Enum TubeStatus. */
public enum TubeStatus {

    /** The pending. */
    PENDING("Subscriber syncing is pending"), /** The consistent failed. */
    CONSISTENT_FAILED("Failed to sync data to consistent subscribers"), /** The consistent succeed. */
    CONSISTENT_SUCCEED("Data synced up successfully to consistent subscribers"),
    SUCCEED("Data synced up successfully to consistent & eventually consistent subscribers");

    /** The status. */
    private String status;

    /** Instantiates a new tube status.
     *
     * @param status
     *            the status */
    private TubeStatus(String status) {
        this.status = status;
    }

    /** To string.
     *
     * @return String. */
    public String toString() {
        return this.status;
    }

}
