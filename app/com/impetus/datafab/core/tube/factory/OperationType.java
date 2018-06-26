package com.impetus.datafab.core.tube.factory;

/** The Enum OperationType. */
public enum OperationType {

   
    /** The find. */
    FIND("find"),

    /** The find one. */
    FIND_ONE("findOne"),

    /** The create. */
    CREATE("create"),

    /** The delete. */
    DELETE("delete"),

    /** The update. */
    UPDATE("update"),
    
    COUNT("count"),
   
    /** Aggregate. */
    AGGREGATE("aggregate"),
    /** Stream */
    STREAM("stream");
	
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
