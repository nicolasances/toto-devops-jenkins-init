package com.imatz.toto.devops.jenkins.init.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

/**
 * This class provides utility methods for performing REST HTTP Calls
 * 
 * @author C308961
 *
 */
public class HTTPCall {

	private String endpoint_;
	private Credentials credentials_;

	public HTTPCall(String endpoint) {
		endpoint_ = endpoint;
	}

	public String call(InputStream inputStream, String method, String contentType, String accept) {

		HttpURLConnection connection = null;
		try {
			
			System.out.println("Starting");

			URL url = new URL(endpoint_);
			connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestMethod(method);
			connection.setRequestProperty("Content-Type", contentType);
			connection.setRequestProperty("Accept", accept);

			if (credentials_ != null) setCredentials(connection);
			
			if (inputStream != null) {
				OutputStream os = connection.getOutputStream();
				
				int i;
				while ((i = inputStream.read()) != -1) {
					os.write(i);
					os.flush();
				}
				
				inputStream.close();
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

			String response = buildResponse(br);

			return response;

		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if (connection != null) connection.disconnect();
		}

	}

	/**
	 * Sets the credentials by setting the HTTP Basic Auth header
	 * 
	 * @param connection
	 * @throws UnsupportedEncodingException 
	 */
	private void setCredentials(HttpURLConnection connection) throws UnsupportedEncodingException {

		String encoding = Base64.getEncoder().encodeToString((credentials_.getUsername() + ":" + credentials_.getPassword()).getBytes("UTF-8"));
		
		connection.setRequestProperty("Authorization", "Basic " + encoding);
	}

	/**
	 * Serializes the information provided in the reader and returns it as a
	 * string
	 * 
	 * @param the
	 *            reader
	 * @return the serialized information
	 * @throws IOException
	 */
	private String buildResponse(BufferedReader reader) throws IOException {

		StringBuffer buffer = new StringBuffer();

		String line = "";
		while ((line = reader.readLine()) != null) {
			buffer.append(" ");
			buffer.append(line);
		}

		return buffer.toString();
	}

	public void setCredentials(Credentials credentials) {
		credentials_ = credentials;
	}

}
