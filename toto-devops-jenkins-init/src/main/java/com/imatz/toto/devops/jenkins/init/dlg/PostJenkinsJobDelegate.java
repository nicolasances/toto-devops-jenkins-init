package com.imatz.toto.devops.jenkins.init.dlg;

import org.springframework.stereotype.Service;

import com.imatz.toto.devops.jenkins.init.jenkins.JenkinsJobDescriptor;
import com.imatz.toto.devops.jenkins.init.jenkins.JenkinsTotoMSJobDescriptor;
import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobRequest;
import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobRequest.Host;
import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobResponse;
import com.imatz.toto.devops.jenkins.init.util.HTTPCall;

@Service
public class PostJenkinsJobDelegate {
	
	public PostJenkinsJobResponse postJenkinsJob(PostJenkinsJobRequest request) {
		
		// 1. Create the Jenkins XML job descriptor
		JenkinsJobDescriptor jenkinsJobDescriptor = new JenkinsTotoMSJobDescriptor(request.getTotoMsName());
		
		// 2. Make the call
		HTTPCall call = new HTTPCall(buildURL(request.getHostDetails(), createJobName(request.getTotoMsName())));
		call.setCredentials(request.getCredentials());
		call.call(jenkinsJobDescriptor.getConfigFileAsStream(), "POST", "text/xml", "application/json");
		
		// 3. Create the response
		PostJenkinsJobResponse response = new PostJenkinsJobResponse();
		
		return response;
	}

	/**
	 * Creates the jenkins job name based on the TOTO MS Name
	 * @param totoMsName
	 * @return
	 */
	private String createJobName(String totoMsName) {
		
		return "build-" + totoMsName;
	}

	/**
	 * Builds the Jenkins URL to create a job
	 * @param hostDetails
	 * @param jobName 
	 * @return
	 */
	private String buildURL(Host hostDetails, String jobName) {
		
		return hostDetails.getProtocol() + "://" + hostDetails.getHost() + ":" + hostDetails.getPort() + "/createItem?name=" + jobName;
		
	}

}
