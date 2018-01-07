package com.imatz.toto.devops.jenkins.init.dlg.mongo;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.imatz.toto.devops.jenkins.init.jenkins.JenkinsJobDescriptor;
import com.imatz.toto.devops.jenkins.init.jenkins.JenkinsNginxJobDescriptor;
import com.imatz.toto.devops.jenkins.init.model.to.CreateMongoJenkinsJobRequest;
import com.imatz.toto.devops.jenkins.init.model.to.CreateMongoJenkinsJobResponse;
import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobRequest.Host;
import com.imatz.toto.devops.jenkins.init.util.HTTPCall;

@Service
public class CreateMongoJenkinsJobDelegate {

	public static final String MONGO_JENKINS_JOB_NAME = "build-mongo";

	public CreateMongoJenkinsJobResponse createMongoJenkinsJob(CreateMongoJenkinsJobRequest request) {

		JenkinsJobDescriptor jenkinsJobDescriptor = new JenkinsNginxJobDescriptor();

		// 2. Make the call
		HTTPCall call = new HTTPCall(buildURL(request.getHostDetails(), MONGO_JENKINS_JOB_NAME));
		call.setCredentials(request.getCredentials());

		try {
			call.call(jenkinsJobDescriptor.getConfigFileAsStream(), "POST", "text/xml", "application/json");
		}
		catch (IOException e) {

			if (e.getMessage().contains("HTTP response code: 400")) {

				CreateMongoJenkinsJobResponse response = new CreateMongoJenkinsJobResponse();
				response.setAlreadyExisting(true);

				return response;
			}

			throw new RuntimeException(e);

		}

		// 3. Create the response
		CreateMongoJenkinsJobResponse response = new CreateMongoJenkinsJobResponse();
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
