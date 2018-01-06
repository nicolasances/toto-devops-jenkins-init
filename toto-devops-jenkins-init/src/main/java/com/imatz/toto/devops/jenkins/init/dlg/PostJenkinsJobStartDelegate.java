package com.imatz.toto.devops.jenkins.init.dlg;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobRequest.Host;
import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobStartRequest;
import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobStartResponse;
import com.imatz.toto.devops.jenkins.init.util.HTTPCall;

/**
 * Start a jenkins job
 * @author nicolas
 *
 */
@Service
public class PostJenkinsJobStartDelegate {

	public PostJenkinsJobStartResponse postJenkinsJobStart(PostJenkinsJobStartRequest request) {
		
		try {
			
			HTTPCall jenkinsJobCall = new HTTPCall(buildURL(request.getJenkinsHost(), request.getJobName()));
			jenkinsJobCall.setCredentials(request.getJenkinsCredentials());
			
			jenkinsJobCall.call(null, "GET", "application/json", "application/json");
			
			return new PostJenkinsJobStartResponse();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Builds the Jenkins URL to create a job
	 * 
	 * @param hostDetails
	 * @param jobName
	 * @return
	 */
	private String buildURL(Host hostDetails, String jobName) {

		return hostDetails.getProtocol() + "://" + hostDetails.getHost() + ":" + hostDetails.getPort() + "/job/" + jobName + "/build?token=toto-build-token";

	}

}
