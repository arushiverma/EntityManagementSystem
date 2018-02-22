package com.target.datastore.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.target.entity.constants.EntityConstants;

public class QueryUtil {
	
	/** The object mapper. **/
    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();


	/**
	 * Parses and converts ObjectId to _id.
	 *
	 * @param objNode the obj node
	 * @param previousKey the previous key
	 * @param prevNode the prev node
	 */
	public static void parseResult(ObjectNode objNode, String previousKey, ObjectNode prevNode) {
	    Iterator<Map.Entry<String, JsonNode>> iterator = objNode.fields();
	    while (iterator.hasNext()) {
	        Map.Entry<String, JsonNode> entry = iterator.next();
	        String key = entry.getKey();
	        Object value = entry.getValue();
	        if (value instanceof ObjectNode) {
	            parseResult((ObjectNode) value, key, objNode);
	        } else if (value instanceof ArrayNode) {
	            int length = ((ArrayNode) value).size();
	            for (int i = 0; i <= length; i++) {
	                Object currentNode = ((ArrayNode) value).get(i);
	                if (currentNode instanceof ObjectNode) {
	                    parseResult((ObjectNode) currentNode, null, null);
	                }
	            }
	        } else if (key.contains("$") && previousKey != null && prevNode != null) {
	            prevNode.put(previousKey, ((JsonNode) value).asText());
	        }
	    }}
	
	
	 /** Parses and converts _id, date to $oid, $date respectively.
    *
    * @param objNode the obj node 
    * @param isFindRequest true if this is a find request */
   public static void parseRequest(ObjectNode objNode, boolean isFindRequest) {
       Iterator<String> fieldNames = objNode.fieldNames();
       while (fieldNames.hasNext()) {
           String key = fieldNames.next();
           Object value = objNode.get(key);
           if (EntityConstants.ID.equals(key)) {

               if (value != null && value instanceof ObjectNode) {
                   parseIdAndDateFields(value, EntityConstants.ID);
               } else {
                   // create node with key $oid
                   value = objNode.get(key).asText();
                   if (!(value == null || EntityConstants.NULL.equals(value) || EntityConstants.EMPTY_STRING.equals(value))) {
                       replaceValueWithNode(objNode, key, value.toString(), EntityConstants.ID);
                   }
               }

           } else if (value instanceof ObjectNode) {
               ObjectNode valueNode = (ObjectNode) value;
               
               parseRequest(valueNode, isFindRequest);
           } else if (value instanceof ArrayNode) {
               processArray(value, isFindRequest);
           } else {
               value = objNode.get(key).asText();
               String valueStr = value.toString();
               
               
               
               if (isValidDate(valueStr)) {
                   // create node with key $date
                   replaceValueWithNode(objNode, key, value.toString(), EntityConstants.DATE);
               } else if (isFindRequest && isValidBoolean(valueStr)) {
                   objNode.put(key, Boolean.parseBoolean(valueStr));
               }
           }
       }
   }
   
  
  public static boolean isValidDate(String inDate) {
      if (inDate==null) {
          return false;
      }
      try {
          if(inDate.contains(EntityConstants.DOUBLE_QUOTES)){
              inDate = inDate.replace(EntityConstants.DOUBLE_QUOTES, EntityConstants.EMPTY_STRING);
          }
          SimpleDateFormat dateFormat = new SimpleDateFormat(EntityConstants.DATE_FORMAT_YYYY_MM_DD_DD_HH_MM_SS_SSSZ);
          dateFormat.setLenient(false);
          dateFormat.parse(inDate.trim());
      } catch (java.text.ParseException pe) {
          return false;
      }
      return true;
  }
  
  public static boolean isValidBoolean(String value){
      if (EntityConstants.TRUE.equalsIgnoreCase(value) || EntityConstants.FALSE.equalsIgnoreCase(value)) {
          return true;
      }
      
      return false;
  }

  private static void processArray(Object value, boolean isFindRequest) {
      int length = ((ArrayNode) value).size();
      for (int i = 0; i <= length; i++) {
          Object currentNode = ((ArrayNode) value).get(i);
          if (currentNode instanceof ObjectNode) {
              parseRequest( currentNode.toString());
          } else if (currentNode instanceof TextNode) {
              ArrayNode valueArrayNode = (ArrayNode) value;
              String valueStr = ((TextNode) currentNode).asText();
              if (isValidDate(valueStr)) {
                  ObjectNode jsonNode = OBJECTMAPPER.createObjectNode();
                  jsonNode.put(EntityConstants.DOLLAR + EntityConstants.DATE, valueStr);
                  valueArrayNode.set(i, jsonNode);
              } else if (isFindRequest && isValidBoolean(valueStr)) {
                  valueArrayNode.remove(i);
                  valueArrayNode.insert(i, Boolean.parseBoolean(valueStr));
              }
          }
      }
  }
  
  
  public static String parseRequest(String doc) {
	  ObjectNode objNode;
	try {
		objNode = OBJECTMAPPER.readValue(doc, ObjectNode.class);
		parseRequest(objNode, false);
	      return objNode.toString();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      return null;
  }
  
  /** This method will Iterate the Array of _ID and convert them into $oid.
   * 
   * @param value
   *            the value
   * @param type
   *            the type **/
  private static void parseIdAndDateFields(Object value, String type) {
      Object innerValueNode = null;
      String innerKey = null;
      Iterator<String> innerFieldNames = ((ObjectNode) value).fieldNames();
  
      while (innerFieldNames.hasNext()) {
          innerKey = innerFieldNames.next();
          if (innerKey.equals(OperatorType.IN.getOperator()) || innerKey.equals(OperatorType.GREATER_THAN.getOperator())
                  || innerKey.equals(OperatorType.GREATER_THAN_EQ_TO.getOperator()) || innerKey.equals(OperatorType.LESS_THAN.getOperator())
                  || innerKey.equals(OperatorType.LESS_THAN_EQ_TO.getOperator())) {
              innerValueNode = ((ObjectNode) value).get(innerKey);
          }
          if (innerValueNode != null) {
              if (innerValueNode instanceof ArrayNode) {
                  int length = ((ArrayNode) innerValueNode).size();
                  ArrayNode newArrayNode = OBJECTMAPPER.createArrayNode();
                  for (int i = 0; i <= length; i++) {
                      Object currentNode = ((ArrayNode) innerValueNode).get(i);
                      if (currentNode instanceof TextNode) {
                          Object id = ((TextNode) currentNode).asText();
                          ObjectNode jsonNode = getOidOrDateNode((String) id, type);
                          newArrayNode.add(jsonNode);
                      }
                  }
                  // handle the scenario having array size > 100
                  if (newArrayNode.size() > 0) {
                      ((ObjectNode) value).put(innerKey, newArrayNode);
                  }
              } else if (innerValueNode instanceof TextNode) {
                  String id = ((JsonNode) innerValueNode).asText();
                  ObjectNode jsonNode = getOidOrDateNode(id, type);
                  ((ObjectNode) value).put(innerKey, jsonNode);
              }
          }
      }
  }
  

  /** Replace _id, date fields with $oid, $date nodes.
  *
  * @param objNode
  *            the obj node
  * @param key
  *            the key
  * @param value
  *            the value
  * @param type
  *            the type */
 public static void replaceValueWithNode(ObjectNode objNode, String key, String value, String type) {
     
     ObjectNode jsonNode = OBJECTMAPPER.createObjectNode();
     String prefix = EntityConstants.DOLLAR;
     if (EntityConstants.ID.equals(type) && !key.equals(EntityConstants.OID) && ObjectId.isValid(value)) {
         jsonNode.put(EntityConstants.OID, value);
         objNode.put(key, jsonNode);
     } else if (EntityConstants.DATE.equals(type) && !key.equals(prefix + EntityConstants.DATE)) {
         jsonNode.put(prefix + EntityConstants.DATE, value);
         objNode.put(key, jsonNode);
     /*} else if ((OperatorType.LESS_THAN.getOperator() + EntityConstants.DATE).equals(type)) {
         ObjectNode lessThanVal = JsonUtil.getObjectNodeInstance();
         lessThanVal.put(prefix + EntityConstants.DATE, value);
         jsonNode.put(OperatorType.LESS_THAN.getOperator(), lessThanVal);
         objNode.put(key, jsonNode);
     }*/}
 }
 
 public static ObjectNode getOidOrDateNode(String value, String type) {
     ObjectNode node = OBJECTMAPPER.createObjectNode();
     if (EntityConstants.ID.equals(type)) {
         node.put(EntityConstants.OID, value);
     } else if (EntityConstants.DATE.equals(type)) {
         node.put(EntityConstants.DOLLAR + EntityConstants.DATE, value);
     }
     return node;
 }

}
