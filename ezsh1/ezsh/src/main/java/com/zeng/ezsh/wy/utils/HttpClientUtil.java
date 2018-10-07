package com.zeng.ezsh.wy.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	
	private static CloseableHttpClient closeableHttpClient;

	private static HttpPost httpPost ;
	
	private static URIBuilder uriBuilder;
	
	private static StringEntity entity;
	
	private static CloseableHttpResponse response;
	
	public static void createConnection(String URI,String paramJson) throws URISyntaxException{
		closeableHttpClient = HttpClients.createDefault();
		uriBuilder = new URIBuilder(URI);
		httpPost = new HttpPost(uriBuilder.build());
		entity = new StringEntity(paramJson, "utf-8");
		entity.setContentEncoding("UTF-8");    
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
	}
	
	public static void createConnection(String URI,Map<String, String> paramMap) throws URISyntaxException {
		closeableHttpClient = HttpClients.createDefault();
		uriBuilder = new URIBuilder(URI);
		for (Map.Entry<String, String> entry: paramMap.entrySet()) {
			uriBuilder.addParameter(entry.getKey(), entry.getValue());
		}
		httpPost = new HttpPost(uriBuilder.build());
		httpPost.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
	}
	
	
	public static String getResponse() throws ClientProtocolException, IOException{
		response = closeableHttpClient.execute(httpPost);
		if (response.getStatusLine().getStatusCode() == 200) {
			return EntityUtils.toString(response.getEntity(), "utf-8");
		}else{
			return null;
		}
	}
	
	public static void closeConnection() throws IOException{
		if(response != null){
			response.close();
		}
		if(closeableHttpClient != null){
			closeableHttpClient.close();
		}
	}
	
	public static int getStatusCode(){
		if(response != null)
			return response.getStatusLine().getStatusCode();
		else
			return 404;
	}
}
