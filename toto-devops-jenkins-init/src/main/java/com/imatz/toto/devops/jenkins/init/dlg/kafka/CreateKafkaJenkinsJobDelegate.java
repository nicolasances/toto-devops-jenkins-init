package com.imatz.toto.devops.jenkins.init.dlg.kafka;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.imatz.toto.devops.jenkins.init.jenkins.JenkinsJobDescriptor;
import com.imatz.toto.devops.jenkins.init.jenkins.JenkinsKafkaJobDescriptor;
import com.imatz.toto.devops.jenkins.init.model.to.CreateKafkaJenkinsJobRequest;
import com.imatz.toto.devops.jenkins.init.model.to.CreateKafkaJenkinsJobResponse;
import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobRequest.Host;
import com.imatz.toto.devops.jenkins.init.util.HTTPCall;

@Service
public class CreateKafkaJenkinsJobDelegate {

	public static final String KAFKA_JENKINS_JOB_NAME = "build-kafka";

	public CreateKafkaJenkinsJobResponse createKafkaJenkinsJob(CreateKafkaJenkinsJobRequest request) {

		JenkinsJobDescriptor jenkinsJobDescriptor = new JenkinsKafkaJobDescriptor();

		// 2. Make the call
		HTTPCall call = new HTTPCall(buildURL(request.getHostDetails(), KAFKA_JENKINS_JOB_NAME));
		call.setCredentials(request.getCredentials());

		try {
			call.call(jenkinsJobDescriptor.getConfigFileAsStream(), "POST", "text/xml", "application/json");
		}
		catch (IOException e) {

			if (e.getMessage().contains("HTTP response code: 400")) {

				CreateKafkaJenkinsJobResponse response = new CreateKafkaJenkinsJobResponse();
				response.setAlreadyExisting(true);

				return response;
			}

			throw new RuntimeException(e);

		}

		// 3. Create the response
		CreateKafkaJenkinsJobResponse response = new CreateKafkaJenkinsJobResponse();
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
