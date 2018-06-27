package com.impetus.entity.constants;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EntityConstants {
	
	/** The date. */
	public static String DATE = "date";
    
    /** The true. */
    public static String TRUE = "true";

    /** The false. */
    public static String FALSE = "false";
    
    /** The id. */
    public static String ID = "_id";
    
    /** Null string. */
    public static String NULL = "null";
    
    /** The empty string. */
    public static String EMPTY_STRING = "";
    
    /** The date format yyyy mm dd dd hh mm ss sssz. */
    public static String DATE_FORMAT_YYYY_MM_DD_DD_HH_MM_SS_SSSZ = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    
    /** Double quotes string. */
    public static String DOUBLE_QUOTES = "\"";
    
    /** Represents space. */
    public static String SPACE = " ";

    /** The empty. */
    public static String EMPTY = "";
    
    /** The dollar. **/
    public static String DOLLAR = "$";

    public static String STAR = "*";

    public static String COMMA = ",";
    
    /** The oid. */
    public static String OID = "$oid";
    
    public static final ObjectMapper OBJECTMAPPER = new ObjectMapper();


}
