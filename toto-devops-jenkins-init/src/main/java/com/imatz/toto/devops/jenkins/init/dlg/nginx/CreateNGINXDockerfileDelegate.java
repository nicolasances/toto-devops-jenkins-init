package com.imatz.toto.devops.jenkins.init.dlg.nginx;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.imatz.toto.devops.jenkins.init.model.to.CreateNGINXDockerfileRequest;
import com.imatz.toto.devops.jenkins.init.model.to.CreateNGINXDockerfileResponse;

/**
 * Creates the Dockerfile for the NGINX proxy container
 * 
 * @author nicolas
 *
 */
@Service
public class CreateNGINXDockerfileDelegate {

	private static String TARGET_FOLDER_NAME = "nginx-setup";

	public CreateNGINXDockerfileResponse createNGINXDockerfile(CreateNGINXDockerfileRequest request) {

		// 1. Create dir if it doesn't exist
		createDir();

		// 2. Create docker file
		File dockerfile = new File("/" + TARGET_FOLDER_NAME + "/Dockerfile");

		// 3. Fill docker file
		createDockerfile(dockerfile);
		
		return new CreateNGINXDockerfileResponse(dockerfile);
	}

	/**
	 * Creates the Dockerfile for the nginx proxy
	 * 
	 * @param dockerfile
	 */
	private void createDockerfile(File dockerfile) {
		
		BufferedWriter writer = null;
		
		try {
			
			writer = new BufferedWriter(new FileWriter(dockerfile));
			
			writer.write("FROM nginx");
			writer.newLine();
			writer.newLine();
			writer.write("COPY nginx.conf /etc/nginx/nginx.conf");
			writer.newLine();
			writer.write("RUN mkdir /certificates");
			writer.newLine();
			writer.write("VOLUME [“/certificates”]");
			writer.newLine();
			
			writer.flush();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if (writer != null) try {
				writer.close();
			}
			catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Creates the target dir for the nginx Dockerfile
	 */
	private File createDir() {

		File dir = new File("/" + TARGET_FOLDER_NAME);

		if (!dir.exists()) dir.mkdir();

		return dir;
	}

}
