package com.imatz.toto.devops.jenkins.init.dlg.nginx;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.imatz.toto.devops.jenkins.init.jenkins.JenkinsJobDescriptor;
import com.imatz.toto.devops.jenkins.init.jenkins.JenkinsNginxJobDescriptor;
import com.imatz.toto.devops.jenkins.init.model.to.CreateNGINXJenkinsJobRequest;
import com.imatz.toto.devops.jenkins.init.model.to.CreateNGINXJenkinsJobResponse;
import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobRequest.Host;
import com.imatz.toto.devops.jenkins.init.util.HTTPCall;

@Service
public class CreateNGINXJenkinsJobDelegate {

	public static final String NGINX_JENKINS_JOB_NAME = "build-nginx";

	public CreateNGINXJenkinsJobResponse createNGINXJenkinsJob(CreateNGINXJenkinsJobRequest request) {

		JenkinsJobDescriptor jenkinsJobDescriptor = new JenkinsNginxJobDescriptor();

		// 2. Make the call
		HTTPCall call = new HTTPCall(buildURL(request.getHostDetails(), NGINX_JENKINS_JOB_NAME));
		call.setCredentials(request.getCredentials());

		try {
			call.call(jenkinsJobDescriptor.getConfigFileAsStream(), "POST", "text/xml", "application/json");
		}
		catch (IOException e) {

			if (e.getMessage().contains("HTTP response code: 400")) {

				CreateNGINXJenkinsJobResponse response = new CreateNGINXJenkinsJobResponse();
				response.setAlreadyExisting(true);

				return response;
			}

			throw new RuntimeException(e);

		}

		// 3. Create the response
		CreateNGINXJenkinsJobResponse response = new CreateNGINXJenkinsJobResponse();
		response.setAlreadyExisting(false);

		return response;

	}

	/**
	 * Builds the Jenkins URL to create a job
	 * 
	 * @param hostDetails
	 * @param jobName
	 * @return
	 */
	private String buildURL(Host hostDetails, String jobName) {

		return hostDetails.getProtocol() + "://" + hostDetails.getHost() + ":" + hostDetails.getPort() + "/createItem?name=" + jobName;

	}

}
