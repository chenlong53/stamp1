package com.detection.demo.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpURLClient {

		public String doPost(String url, String params, String appId, String token, String requestId, String requestTime) throws Exception {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);// 创建httpPost   
	    	httpPost.setHeader("Accept", "*/*");
	    	httpPost.setHeader("Content-Type", "application/json");
	    	httpPost.setHeader("appId", appId);
	    	httpPost.setHeader("token", token);
	    	httpPost.setHeader("requestId", requestId);
	    	httpPost.setHeader("requestTime", requestTime);
	    	String charSet = "UTF-8";
	    	StringEntity entity = new StringEntity(params, charSet);
	    	httpPost.setEntity(entity);
	        CloseableHttpResponse response = null;
	        try {
	        	response = httpclient.execute(httpPost);
	            StatusLine status = response.getStatusLine();
	            int state = status.getStatusCode();
	            if (state == HttpStatus.SC_OK) {
	            	HttpEntity responseEntity = response.getEntity();
	            	String jsonString = EntityUtils.toString(responseEntity);
	            	return jsonString;
	            }
	            else{
	            	throw new HttpErrorException();
				}
	        }catch(Exception e){
	        	e.printStackTrace();
	        	throw new HttpErrorException();
			}
	        finally {
	            if (response != null) {
	                try {
	                    response.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		}
	}

    

