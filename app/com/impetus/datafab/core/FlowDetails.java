package com.impetus.datafab.core;

import java.util.Map;
import java.util.TreeMap;

/** The Class FlowDetails. */
public class FlowDetails {

    /** The dataflow tuples. */
    private Map<Integer, StoreTuple> dataflowTuples;

    /** The template class. */
    private String templateClass;

    /** Instantiates a new flow details. */
    public FlowDetails() {
        dataflowTuples = new TreeMap<Integer, StoreTuple>();
    }

    /** Gets the dataflow tuples.
     *
     * @return the dataflowTuple */
    public Map<Integer, StoreTuple> getDataflowTuples() {
        return dataflowTuples;
    }

    /** Adds the to dataflow tuples.
     *
     * @param tupleId
     *            the tuple id
     * @param storeTuple
     *            the store tuple */
    public void addToDataflowTuples(Integer tupleId, StoreTuple storeTuple) {
        this.dataflowTuples.put(tupleId, storeTuple);
    }

    /** Gets the template class.
     *
     * @return the templateClass */
    public String getTemplateClass() {
        return templateClass;
    }

    /** Sets the template class.
     *
     * @param templateClass
     *            the templateClass to set */
    public void setTemplateClass(String templateClass) {
        this.templateClass = templateClass;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FlowDetails [dataflowTuples=" + dataflowTuples + ", templateClass=" + templateClass + "]";
    }

}
