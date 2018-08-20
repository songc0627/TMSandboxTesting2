package co.nz.assurity.test.TMSandboxTesting2.apicontroller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;


public class APIRequest {

	private HttpClient client;
	private String url;

	/**
	 * Constructor for APIController
	 * 
	 * @param url
	 */
	public APIRequest(String url) {
		client = HttpClientBuilder.create().build();
		this.url = url;
	}

	public APIResponse get(String path) {
		return get(path, null);
	}

	/**
	 * Executes GET req and returns response json.
	 * 
	 * @param path
	 * @param headers
	 * @return
	 */
	public APIResponse get(String path, HashMap<String, String> headers) {
		HttpGet request = new HttpGet(url + path);
		HttpResponse response;
		/*
		 * The response object which holds the details of the response.
		 */
		APIResponse resResponse = new APIResponse();
		StringBuffer responseString = new StringBuffer();
		try {
			/*
			 * Setting the headers for the request
			 */
			if (headers != null) {
				Set<String> keys = headers.keySet();
				for (String key : keys) {
					request.addHeader(key, headers.get(key));
				}
			}
			/* 
			 * Executing the GET operation
			 */
			response = client.execute(request);
			
			/*
			 * Obtaining the response body from the response stream.
			 */
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				responseString.append(line);
			}
			
			/*
			 * Setting values for the response object
			 */
			resResponse.setResponseBody(responseString.toString());
			resResponse.setResponseCode(response.getStatusLine().getStatusCode());
			resResponse.setResponseMessage(response.getStatusLine().getReasonPhrase());
			Header[] rheaders = response.getAllHeaders();
			for (Header header : rheaders) {
				resResponse.setHeader(header.getName(), header.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * Returns the response object
		 */
		return resResponse;
	}
}
