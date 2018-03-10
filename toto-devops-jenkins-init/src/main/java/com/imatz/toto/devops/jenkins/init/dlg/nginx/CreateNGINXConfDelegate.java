package com.imatz.toto.devops.jenkins.init.dlg.nginx;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
		createNginxConf(confFile, microservices, request.getExcludedMicroservices(), request.getProd());

		return new CreateNGINXConfDelegateResponse(confFile);
	}

	/**
	 * Creates the content of the nginx configuration file nginx.conf
	 * 
	 * @param confFile
	 * @param microservices
	 *            the list of microservices projects retrieved from github
	 * @param excludedMicroservices
	 *            list of microservices (names, e.g. toto-ms-gym) that are to be
	 *            excluded from nginx
	 */
	private void createNginxConf(File confFile, GetGithubMicroservicesResponse microservices, List<String> excludedMicroservices, Boolean prod) {

		BufferedWriter writer = null;

		try {

			writer = new BufferedWriter(new FileWriter(confFile));

			writer.write("http {");
			writer.newLine();
			writer.write("server {");
			writer.newLine();
			
			if (prod) {
				writer.write("listen 443 ssl;");
				writer.newLine();
				writer.write("server_name imatz.info;");
				writer.newLine();
				writer.write("ssl_certificate /certificates/fullchain1.pem;");
				writer.newLine();
				writer.write("ssl_certificate_key /certificates/privkey1.pem;");
				writer.newLine();
			}

			writer.flush();
			
			if (prod) {
				writer.write("location /toto/ {");
				writer.newLine();
				writer.write("proxy_pass http://toto/;");
				writer.newLine();
				writer.write("}");
				writer.newLine();
			}

			for (TotoMSProject msProject : microservices.getProjects()) {

				String msName = msProject.getName();

				if (excluded(msName, excludedMicroservices)) continue;

				writer.newLine();
				if (msName.contains("toto-ms-")) writer.write("location /" + msName.substring("toto-ms-".length()) + "/ {");
				else writer.write("location /" + msName.substring("toto-nodems-".length()) + "/ {");
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
	 * Checks if the specified MS has been excluded from nginx
	 * 
	 * @param msName
	 * @param excludedMicroservices
	 * @return
	 */
	private boolean excluded(String msName, List<String> excludedMicroservices) {
		
		for (String excluded : excludedMicroservices) {
			if (msName.equalsIgnoreCase(excluded)) return true;
		}
		
		return false;
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
