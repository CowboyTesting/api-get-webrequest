package com.ea.eadp.data.aem.test.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class WebRequest
{
	private static Map<String, String> newMap;
	private static ArrayList<Map<String, String>> mapArrayList;
	private static HttpPost httpPost;
	private static HttpGet httpGet;
	private static HttpResponse httpResponse;
	
	/**
	 * A set of string values used to determine if we should submit a POST or GET request
	 */
	private static final Set<String> postValues = new HashSet<String>(Arrays.asList("insert","update",
			"audience/count/criteria","audience/userids/criteria"));
	
	public WebRequest(){
		httpPost = new HttpPost();
		httpGet = new HttpGet();
	}
	
	/**
	 * Public method for accessing the PRM APIs
	 * @param prop - Properties file for test specific data
	 * @param targetHost - HttpHost for test
	 * @param credsProvider - Credentials used for authenticating HTTP Request
	 * @param uriAppend - Specific text to append onto URI when querying API
	 * @return ArrayList value containing hashmaps of all JSON node values
	 * @throws Exception
	 */
	public static ArrayList<Map<String, String>> newWebRequest(Properties prop, 
			HttpHost targetHost, 
			CredentialsProvider credsProvider,
			String uriAppend,
			Map<String, String> uriParameters,
			URIBuilder uri) 
			throws Exception
	{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		
		try {
			BasicHttpContext localContext = setHttpContext(targetHost,credsProvider, httpClient);
			if (uriAppend != null){
				for (String key : uriParameters.keySet()){
					if (uriParameters.get(key) != null){
						uri.addParameter(key, uriParameters.get(key));
					}
				}
				httpGet = new HttpGet(uri.toString());
				httpResponse = httpClient.execute(targetHost,httpGet,localContext);
			}else{
				JSONObject jobject = new JSONObject(uriParameters);
				StringEntity strEntity = new StringEntity(jobject.toString());
				httpPost = new HttpPost(uri.toString());
				httpPost.setEntity(strEntity);
				httpPost.setHeader("Content-type", "application/json");
				httpResponse = httpClient.execute(targetHost,httpPost,localContext);
			}
			
			HttpEntity entity = callHttpHost(uriAppend);
			
			EntityUtils.consume(entity);
		} catch (ClientProtocolException e ) {
			e.printStackTrace();
		}
		finally{
			httpClient.getConnectionManager().shutdown();
		}
		return mapArrayList;
	}

	/**
	 * @param uriAppend
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws ParseException
	 */
	private static HttpEntity callHttpHost(String uriAppend)
			throws UnsupportedEncodingException, IOException,
			IllegalStateException, ParseException{
		HttpEntity entity = httpResponse.getEntity();
		if(entity != null){
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
			StringBuilder builder = new StringBuilder();
			for(String line = null; (line = reader.readLine()) != null;){
				builder.append(line).append("\n");
			}
			
			if(uriAppend != null){
				parseJSONResponse(builder);
			}else{
				System.out.println(httpResponse.toString());
			}
		}
		return entity;
	}

	/**
	 * @param builder
	 * @throws ParseException
	 */
	private static void parseJSONResponse(StringBuilder builder)
			throws ParseException
	{
		JSONTokener tokener = new JSONTokener(builder.toString());
		try{
			JSONObject result = new JSONObject(tokener);
			newMap = parseJSONObject(result);
			mapArrayList.add(newMap);
		} catch(JSONException e){
			try{
				tokener.back();
				JSONArray result = new JSONArray(tokener);
				mapArrayList = parseJSONArray(result);
			} catch (JSONException je){
				System.out.println(builder.toString());
				System.out.println(tokener.toString());
				je.printStackTrace();
			}
		}
	}

	/**
	 * Sets the overall context for the HTTP session including authentication
	 * @param targetHost
	 * @param credsProvider
	 * @param httpClient
	 * @return BasicHttpContext (X64)
	 */
	private static BasicHttpContext setHttpContext(HttpHost targetHost,
			CredentialsProvider credsProvider, DefaultHttpClient httpClient){
		httpClient.setCredentialsProvider(credsProvider);
		AuthCache authCache = new BasicAuthCache();
		BasicScheme basicScheme = new BasicScheme();
		authCache.put(targetHost, basicScheme);
		BasicHttpContext localContext = new BasicHttpContext();
		localContext.setAttribute(ClientContext.AUTH_CACHE, authCache);
		return localContext;
	}
	
	private static ArrayList<Map<String, String>> parseJSONArray(JSONArray jsonArr) throws ParseException, JSONException{
		ArrayList<Map<String, String>> mapArray = new ArrayList<Map <String, String>>();
	    for (int k = 0; k < jsonArr.length(); k++) {
	        if (jsonArr.get(k) instanceof JSONObject) {
	        	mapArray.add(parseJSONObject((JSONObject) jsonArr.getJSONObject(k)));
	        } else {
	            System.out.println(jsonArr.get(k));
	        }
	    }
	    
	    return mapArray;
	}
	
	private static Map<String, String> parseJSONObject(JSONObject jsonObj) throws ParseException, JSONException{
		Iterator<?> keys = jsonObj.keys();
		Map<String, String> jsonMap = new HashMap<String, String>();
	    while (keys.hasNext()) {
	    	String key = String.valueOf(keys.next());
	    	if(key != null){
	    		jsonMap.put(key,String.valueOf(jsonObj.get(key)));
	    	}
	    }
	    
	    return jsonMap;
	}
}
