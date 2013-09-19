/**
 * EA Private Ltd. All rights reserved 2012
 */
package com.ea.eadp.data.aem.test.apitest.segments;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import junit.framework.Assert;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ea.eadp.data.aem.test.model.Segment;
import com.ea.eadp.data.aem.test.web.WebRequest;

/**
 * @author cstuehrenberg
 *
 */
public class SegmentQuery
{
	CredentialsProvider credsProvider = new BasicCredentialsProvider();
	private Properties prop = new Properties();
	private HttpHost targetHost;
	private String uriAppend = null;
	private List<Map<String,String>> jsonArray = new ArrayList<Map<String,String>>();
	private Map<String, String> uriParameters = new HashMap<String, String>();

	/**
	 * Initializes a new segment object
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		this.credsProvider = new BasicCredentialsProvider();
		this.prop = new Properties();
		this.jsonArray = new ArrayList<Map<String,String>>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		
	}

	/**
	 * Test case checking the count of segments in the development instance
	 * @throws IOException
	 */
	@Test
	public void testGetAllSegments() throws Exception
	{
		List<Segment> listSegments = new ArrayList<Segment>();
		uriAppend = "all";
		uriParameters.put("type", "SEGMENT");
		try{
			prop.load(WebRequest.class.getResourceAsStream("/config.properties"));
			targetHost = new HttpHost(prop.getProperty("host","node73-188.prod-aws.eadpdata.ea.com"),
					Integer.parseInt(prop.getProperty("port","8080")),
					prop.getProperty("scheme","http"));
			credsProvider.setCredentials(
					new AuthScope(targetHost.getHostName(),targetHost.getPort()), 
					new UsernamePasswordCredentials(
							prop.getProperty("username","bittick"),
							prop.getProperty("password","m6ACx7ad")));
			
			URIBuilder uri = new URIBuilder(prop.getProperty("queryUri")+uriAppend);
			
			jsonArray = WebRequest.newWebRequest(prop, targetHost, credsProvider, uriAppend, uriParameters, uri);
		} catch (Exception e){
			fail(e.getStackTrace().toString());
		}
		
		for(Map<String,String> map : jsonArray){
			Segment segment = new Segment(map);
			listSegments.add(segment);
		}
		
		Assert.assertEquals(3, jsonArray.size());
		Assert.assertEquals(3, listSegments.size());
	}

}
