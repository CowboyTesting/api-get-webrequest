/**
 * EA Private Ltd. All rights reserved 2012
 */
package com.ea.eadp.data.aem.test.model;

/**
 * @author cstuehrenberg
 *
 */

import java.io.Serializable;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ea.eadp.data.aem.test.model.AEMTestTools;

public class Segment implements Serializable{
	private static final long serialVersionUID = 1L;
	private static String count;
	private static String countUpdatedTimestamp;
	private static String createdBy;
	private static String createdByTimestamp;
	private static String criteria;
	private static String description;
	private static String eaGroupId;
	private static String parentSegmentId;
	private static String predicateTree;
	private static String segmentId;
	private static String segmentName;
	private static String segementType;
	private static String snapshot;
	private static String snapshotUserIdFileName;
	private static String type = "SEGMENT";
	private static String updatedBy;
	private static String updatedTimestamp;
	
	private static AEMTestTools tools = new AEMTestTools();
	
 	public static String getCount()
	{
		return count;
	}
	public static void setCount(String count)
	{
		Segment.count = count;
	}
	public static String getCountUpdatedTimestamp()
	{
		return countUpdatedTimestamp;
	}
	public static void setCountUpdatedTimestamp(String countUpdatedTimestamp)
	{
		Segment.countUpdatedTimestamp = countUpdatedTimestamp;
	}
	public static String getSegmentId()
	{
		return segmentId;
	}
	public static void setSegmentId(String segmentId)
	{
		Segment.segmentId = segmentId;
	}
	public static String getSegmentName()
	{
		return segmentName;
	}
	public static void setSegmentName(String segmentName)
	{
		Segment.segmentName = segmentName;
	}
	public static String getCriteria()
	{
		return criteria;
	}
	public static void setCriteria(String criteria)
	{
		Segment.criteria = criteria;
	}
	public static String getType()
	{
		return type;
	}
	public static void setType(String type)
	{
		Segment.type = type;
	}
	public static String getUpdatedBy()
	{
		return updatedBy;
	}
	public static void setUpdatedBy(String updatedBy)
	{
		Segment.updatedBy = updatedBy;
	}
	public static String getCreatedBy()
	{
		return createdBy;
	}
	public static void setCreatedBy(String createdBy)
	{
		Segment.createdBy = createdBy;
	}
	public static String getDescription()
	{
		return description;
	}
	public static void setDescription(String description)
	{
		Segment.description = description;
	}
	public static String getEAGroupId()
	{
		return eaGroupId;
	}
	public static void setEAGroupId(String eaGroupId)
	{
		Segment.eaGroupId = eaGroupId;
	}
	public static String getPredicateTree()
	{
		return predicateTree;
	}
	public static void setPredicateTree(String predicateTree)
	{
		Segment.predicateTree = predicateTree;
	}
	public static String getCreatedByTimestamp()
	{
		return createdByTimestamp;
	}
	public static void setCreatedByTimestamp(String createdByTimestamp)
	{
		Segment.createdByTimestamp = createdByTimestamp;
	}
	public static String getParentSegmentId()
	{
		return parentSegmentId;
	}
	public static void setParentSegmentId(String parentSegmentId)
	{
		Segment.parentSegmentId = parentSegmentId;
	}
	public static String getSegementType()
	{
		return segementType;
	}
	public static void setSegementType(String segementType)
	{
		Segment.segementType = segementType;
	}
	public static String getSnapshot()
	{
		return snapshot;
	}
	public static void setSnapshot(String snapshot)
	{
		Segment.snapshot = snapshot;
	}
	public static String getSnapshotUserIdFileName()
	{
		return snapshotUserIdFileName;
	}
	public static void setSnapshotUserIdFileName(String snapshotUserIdFileName)
	{
		Segment.snapshotUserIdFileName = snapshotUserIdFileName;
	}
	public static String getUpdatedTimestamp()
	{
		return updatedTimestamp;
	}
	public static void setUpdatedTimestamp(String updatedTimestamp)
	{
		Segment.updatedTimestamp = updatedTimestamp;
	}
	
	public Segment(){
		Segment.setType("SEGMENT");
	}
	
	public Segment(Map<String, String> values)
	{	
		Segment.setType("SEGMENT");
		for(Map.Entry<String, String> q : values.entrySet()){
			String key = q.getKey().toLowerCase();
			switch(key){
				case "count": Segment.setCount(q.getValue()); break;
				case "countupdatedtimestamp": Segment.setCountUpdatedTimestamp(q.getValue()); break;
				case "createdby": Segment.setCreatedBy(q.getValue()); break;
				case "createdbytimestamp": Segment.setCreatedByTimestamp(q.getValue()); break;
				case "criteria": Segment.setCriteria(q.getValue()); break;
				case "description": Segment.setDescription(q.getValue()); break;
				case "eagroupid": Segment.setEAGroupId(q.getValue()); break;
				case "parentsegmentid": Segment.setParentSegmentId(q.getValue()); break;
				case "predicatetree": Segment.setPredicateTree(q.getValue()); break;
				case "segmentid": Segment.setSegmentId(q.getValue()); break;
				case "segmentname": Segment.setSegmentName(q.getValue()); break;
				case "segementtype": Segment.setSegementType(q.getValue()); break;
				case "snapshot": Segment.setSnapshot(values.get(key)); break;
				case "snapshotuseridfilename": Segment.setSnapshotUserIdFileName(q.getValue()); break;
				case "type": Segment.setType(q.getValue()); break;
				case "updatedby": Segment.setUpdatedBy(q.getValue()); break;
				case "updatedtimestamp": Segment.setUpdatedTimestamp(q.getValue()); break;
			}
		}
	}
	
	/**
	 * Public method for getting all current segment values
	 * @return Map<String, String> - A hashmap with all current segment values
	 */
	public Map<String, String> getSegmentValues(){
		Map<String, String> values = new HashMap<String, String>();
		
		values.put("count", Segment.getCount());
		values.put("countUpdatedTimestamp", Segment.getCountUpdatedTimestamp());
		values.put("createdBy", Segment.getCreatedBy());
		values.put("createdTimestamp", Segment.getCreatedByTimestamp());
		values.put("criteria", Segment.getCriteria());
		values.put("description", Segment.getDescription());
		values.put("eagroupId", Segment.getEAGroupId());
		values.put("parentSegmentId", Segment.getParentSegmentId());
		values.put("predicateTree", Segment.getPredicateTree());
		values.put("segmentId", Segment.getSegmentId());
		values.put("segmentName", Segment.getSegmentName());
		values.put("segmentType", Segment.getSegementType());
		values.put("snapshot", Segment.getSnapshot());
		values.put("snapshotUserIdFileName", Segment.getSnapshotUserIdFileName());
		values.put("type", Segment.getType());
		values.put("updatedBy", Segment.getUpdatedBy());
		values.put("updatedByTimestamp", Segment.getUpdatedTimestamp());
		
		return values;
	}
	
	/**
	 * Clears all values used by test cases
	 */
	public void clearAll(){
		Segment.count = null;
		Segment.countUpdatedTimestamp = null;
		Segment.createdBy = null;
		Segment.createdByTimestamp = null;
		Segment.criteria = null;
		Segment.description = null;
		Segment.eaGroupId = null;
		Segment.parentSegmentId = null;
		Segment.predicateTree = null;
		Segment.segmentId = null;
		Segment.segmentName = null;
		Segment.snapshot = null;
		Segment.snapshotUserIdFileName = null;
		Segment.segementType = null;
		Segment.updatedBy = null;
		Segment.updatedTimestamp = null;
	}
	
	/**
	 * Generates a basic set of random values required to insert a new segment
	 * @return 
	 */
	public void random(){
		Segment.count = String.valueOf(tools.getRandomNumber(30000000));
		Segment.description = tools.getRandomString(25);
		Segment.segmentName = tools.getRandomString(10);
		Segment.type = "SEGMENT";
		Segment.createdBy = tools.getRandomString(7);
		Segment.updatedBy = tools.getRandomString(7);
		Segment.criteria = tools.getRandomString(50);
		Segment.predicateTree = tools.getRandomString(100);
	}
}
