package com.impetus.datafab.core.tube.factory;

/** The Enum OperationType. */
public enum OperationType {

    /** The search. */
    SEARCH("search"),

    /** The find. */
    FIND("find"),

    /** The find one. */
    FIND_ONE("findOne"),

    /** The create. */
    CREATE("create"),

    /** The get. */
    GET("get"),

    /** The delete. */
    DELETE("delete"),

    /** The update. */
    UPDATE("update"),

    /** update Solr Index  */
    UPDATE_INDEX("updateIndex"),

    /** The find by join. */
    FIND_BY_JOIN("findByJoin"),

    /** The hybrid join. */
    HYBRID_JOIN("hybridJoin"),

    /** The find intact. */
    FIND_INTACT("findIntact"),

    /** Aggregate. */
    AGGREGATE("aggregate"),

    /** Mongo Aggregate. */
    MONGO_AGGREGATE("mongoAggregate"),

    /** The sync. */
    SYNC("sync"),

    /** Stream */
    STREAM("stream"),

    /** index **/
    INDEX("index"),

    /** Find Add **/
    FIND_ALL("findAll"),

    /**The FIND_AND_MODIFY BY ID*/
    FIND_AND_MODIFY_BY_ID("findAndModifyById"),

    /**The FIND_AND_MODIFY BY FILTER*/
    FIND_AND_MODIFY_BY_FILTER("findAndModifyByFilter"),

    /**The FIND_AND_MODIFY For SOLR*/
    FIND_AND_MODIFY_SOLR("findAndModifysolr"),

    /** The bulk create. */
    BULK_CREATE("bulkCreate"),

    /** The count. */
    COUNT("count"),

    /** The bulk update. */
    BULK_UPDATE("bulkUpdate"),

    /** The bulk delete. */
    BULK_DELETE("bulkDelete"),

    /** The find by cursor. **/
    FIND_BY_CURSOR("findByCursor"),

    /** The find in solr. */
    FIND_IN_SOLR("findInSolr"),

    /** The count by join. */
    COUNT_BY_JOIN("countByJoin"),

    /** The Aggregate By Single Join. */
    AGGREGATE_BY_JOIN("aggregateByJoin"),

    /** The dataLoad operation. */
    DATA_LOAD("dataLoad"),

    /** The detailed find. */
    REVERSE_FIND("reverseFind"),

    /** The reverse hybrid find. */
    REVERSE_HYBRID_FIND("reverseHybridFind"),

    /** The immediate update. */
    IMMEDIATE_BULK_UPDATE ("immediateBulkUpdate"),

    /** The force delete. */
    FORCE_DELETE("forceDelete"),

    /** The find by Criteria will read the filter information from Filter Pojo from its criteria, instead of request Json. */
    FIND_BY_CRITERIA("findByCriteria"),

    /** The count by Criteria will read the filter information from Filter Pojo i.e. from its criteria, instead of request Json. Mostly
     * required during delete operation */
    COUNT_BY_CRITERIA("countByCriteria"),

    /** The hybrid count. */
    HYBRID_COUNT("hybridCount"),

    /** The retry transaction.. */
    RETRY_TRANSACTION("retryTransaction"),

    /** The bulk load. */
    BULK_LOAD("bulkload"),

    /** The bulk load. */
    FIND_ASSOCIATION("findAssociation"),

    /** The Soft Delete. */
    SOFT_DELETE("softDelete"),

    /** The Bulk Delete. */
    DELETE_BY_FILTER("deleteByFilter"),

    /** The Bulk expire. */
    LOGICAL_DELETE("logicalDelete");

    /** The operation. **/
    private String operation;

    /** Operation type.
     *
     * @param operation
     *            the operation */
    private OperationType(final String operation) {
        this.operation = operation;
    }

    /** Gets the operation.
     *
     * @return the operation */
    public String getOperation() {
        return operation;
    }

    /** Sets the operation.
     *
     * @param operation
     *            the new operation */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /** Returns OperationType enum for given operation value.
     *
     * @param operation
     *            the operation.
     * @return operation type. */
    public static OperationType getOperationType(String operation) {
        if (operation != null) {
            for (OperationType operationType : OperationType.values()) {
                if (operationType.getOperation().equalsIgnoreCase(operation)) {
                    return operationType;
                }
            }
        }
        return null;
    }
}
