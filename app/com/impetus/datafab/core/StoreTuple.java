package com.impetus.datafab.core;

import java.util.List;

/** The Class StoreTuple. */
public class StoreTuple {

    /** The store. */
    private String store;

    /** The dependencies. */
    private List<String> dependencies;

    /** The Operation. */
    private String operation;

    /** Gets the store.
     * 
     * @return the store */
    public String getStore() {
        return store;
    }

    /** Gets the dependencies.
     * 
     * @return the dependencies */
    public List<String> getDependencies() {
        return dependencies;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "StoreTuple [store=" + store + ", dependencies=" + dependencies + ", operation=" + operation + "]";
    }

    /** Sets the dependencies.
     * 
     * @param dependencies
     *            the new dependencies */
    public void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    /** Sets the store.
     * 
     * @param store
     *            the store to set */
    public void setStore(String store) {
        this.store = store;
    }

    /** Sets the operation.
     * 
     * @param operation
     *            the operation to set */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /** Gets the operation.
     * 
     * @return the operation */
    public String getOperation() {
        return operation;
    }
}
