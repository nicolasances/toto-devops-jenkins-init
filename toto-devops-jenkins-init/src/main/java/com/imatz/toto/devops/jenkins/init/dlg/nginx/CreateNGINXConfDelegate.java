package com.imatz.toto.devops.jenkins.init.dlg.nginx;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imatz.toto.devops.jenkins.init.dlg.GetGithubMicroservicesDelegate;
import com.imatz.toto.devops.jenkins.init.model.to.CreateNGINXConfDelegateRequest;
import com.imatz.toto.devops.jenkins.init.model.to.CreateNGINXConfDelegateResponse;
import com.imatz.toto.devops.jenkins.init.model.to.GetGithubMicroservicesResponse;
import com.imatz.toto.devops.jenkins.init.model.to.GetGithubMicroservicesResponse.TotoMSProject;

/**
 * Creates the configuration file of nginx and puts in /nginx-setup
 * 
 * @author nicolas
 *
 */
@Service
public class CreateNGINXConfDelegate {

	private static String TARGET_FOLDER_NAME = "nginx-setup";
	
	@Autowired
	private GetGithubMicroservicesDelegate getGithubMicroservicesDelegate_;

	public CreateNGINXConfDelegateResponse createNGINXConfDelegate(CreateNGINXConfDelegateRequest request) {
		
		// 1. Create directory
		createDir();
		
		// 2. Create conf file
		File confFile = new File("/" + TARGET_FOLDER_NAME + "/nginx.conf");
		
		// 3. Get Toto MS repositories
		GetGithubMicroservicesResponse microservices = getGithubMicroservicesDelegate_.getGithubMicroservices();
		
		// 4. Create conf file content
		createNginxConf(confFile, microservices);

		return new CreateNGINXConfDelegateResponse(confFile);
	}

	/**
	 * Creates the content of the nginx configuration file nginx.conf
	 * @param confFile
	 * @param microservices
	 */
	private void createNginxConf(File confFile, GetGithubMicroservicesResponse microservices) {
		
		BufferedWriter writer = null;
		
		try {
			
			writer = new BufferedWriter(new FileWriter(confFile));
			
			writer.write("http {");
			writer.newLine();
			writer.write("server {");
			writer.newLine();
			
			writer.flush();
			
			for (TotoMSProject msProject : microservices.getProjects()) {
				
				String msName = msProject.getName();
				
				writer.newLine();
				writer.write("location /" + msName.substring("toto-ms-".length()) + "/ {");
				writer.newLine();
				writer.write("proxy_pass http://" + msName + ":8080/;");
				writer.newLine();
				writer.write("}");
				writer.newLine();
				
				writer.flush();
			}
			
			writer.write("}");
			writer.newLine();
			writer.write("}");
			writer.write("events {}");
			
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
	 * Creates the target dir for the nginx conf file
	 */
	private File createDir() {
		
		File dir = new File("/" + TARGET_FOLDER_NAME);
		
		if (!dir.exists()) dir.mkdir();
		
		return dir;
	}

}
