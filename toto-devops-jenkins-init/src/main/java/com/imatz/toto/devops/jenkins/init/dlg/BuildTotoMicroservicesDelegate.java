package com.imatz.toto.devops.jenkins.init.dlg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imatz.toto.devops.jenkins.init.model.to.BuildTotoMicroservicesRequest;
import com.imatz.toto.devops.jenkins.init.model.to.BuildTotoMicroservicesResponse;
import com.imatz.toto.devops.jenkins.init.model.to.GetJenkinsMicroservicesJobsRequest;
import com.imatz.toto.devops.jenkins.init.model.to.GetJenkinsMicroservicesJobsResponse;
import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobStartRequest;

/**
 * This service starts building all the Toto Microservice Jobs found on Jenkins
 * 
 * @author nicolas
 *
 */
@Service
public class BuildTotoMicroservicesDelegate {
	
	@Autowired
	private GetJenkinsMicroservicesJobsDelegate getTotoMSJobs_;
	
	@Autowired
	private PostJenkinsJobStartDelegate postJenkinsJobStartDelegate_;
	
	public BuildTotoMicroservicesResponse buildTotoMicroservices(BuildTotoMicroservicesRequest request) {
		
		// 1. Get the list of microservices to build
		GetJenkinsMicroservicesJobsRequest req = new GetJenkinsMicroservicesJobsRequest();
		req.setJenkinsCredentials(request.getJenkinsCredentials());
		req.setJenkinsHost(request.getJenkinsHost());
		
		GetJenkinsMicroservicesJobsResponse jobs = getTotoMSJobs_.getJenkinsMicroservicesJobs(req);
		
		// 2. Trigger the build of each microservice
		for (String job : jobs.getJobs()) {
			
			PostJenkinsJobStartRequest startJobRequest = new PostJenkinsJobStartRequest();
			startJobRequest.setJenkinsCredentials(request.getJenkinsCredentials());
			startJobRequest.setJenkinsHost(request.getJenkinsHost());
			startJobRequest.setJobName(job);
			
			postJenkinsJobStartDelegate_.postJenkinsJobStart(startJobRequest);
		}
		
		return new BuildTotoMicroservicesResponse();
	}

}
