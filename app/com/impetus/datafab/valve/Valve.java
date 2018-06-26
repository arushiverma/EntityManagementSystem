package com.impetus.datafab.valve;

/** The base of all consistent & evetually consistent subscribers.
 * 
 * @param <ResponseType>
 *            the generic type */
public interface Valve<ResponseType> {

    /** Gets the name.
     * 
     * @return the name */
    String getName();

}