package com.impetus.datastore.utils;

/** Defines Operator types. */
public enum OperatorType {

    /** The in. */
    IN("$in"),
    /** The not in. */
    NOT_IN("$nin"),
    /** The and. */
    AND("$and"),
    /** The or. */
    OR("$or"),
    /** The greater than. */
    GREATER_THAN("$gt"),
    /** The greater than eq to. */
    GREATER_THAN_EQ_TO("$gte"),
    /** The less than. */
    LESS_THAN("$lt"),
    /** The less than eq to. */
    LESS_THAN_EQ_TO("$lte"),
    /** The regex. */
    REGEX("$regex"),
    /** The desc. */
    DESC("-1"),
    /** The asc. */
    ASC("1"),
    /** The not eq to. */
    NOT_EQ_TO("$ne"),
    /** The delete. */
    DELETE("$delete"),
    /** The upsert. */
    UPSERT("$upsert"),
    /** The exists. */
    EXISTS("$exists"),
    /** First operator for aggregate operation. **/
    FIRST("$first"),
    /** Sum operator for aggregate operation. **/
    SUM("$sum"),
    /** Min operator for aggregate operation. **/
    MIN("$min"),
    /** Max operator for aggregate operation. **/
    MAX("$max");

    /** The operator. **/
    private String operator;

    /** Operator type.
     * 
     * @param operator
     *            the operator */
    private OperatorType(final String operator) {
        this.operator = operator;
    }

    /** Gets the operator.
     * 
     * @return the operator */
    public String getOperator() {
        return operator;
    }

    /** Sets the operator.
     * 
     * @param operator
     *            the operator to set */
    public void setOperator(final String operator) {
        this.operator = operator;
    }

    /** Get operator type by name.
     * 
     * @param name
     *            the name
     * @return the operator type */
    public static OperatorType getOperatorType(final String name) {
        for (OperatorType type : OperatorType.values()) {
            if (type.getOperator().equals(name)) {
                return type;
            }
        }
        return null;
    }
}
