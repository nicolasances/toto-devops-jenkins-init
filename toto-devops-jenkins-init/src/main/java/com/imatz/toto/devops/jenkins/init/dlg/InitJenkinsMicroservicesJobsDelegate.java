package com.imatz.toto.devops.jenkins.init.dlg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imatz.toto.devops.jenkins.init.model.to.GetGithubMicroservicesResponse;
import com.imatz.toto.devops.jenkins.init.model.to.InitJenkinsMicroservicesJobsRequest;
import com.imatz.toto.devops.jenkins.init.model.to.InitJenkinsMicroservicesJobsResponse;
import com.imatz.toto.devops.jenkins.init.model.to.GetGithubMicroservicesResponse.TotoMSProject;
import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobRequest;
import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobResponse;

@Service
public class InitJenkinsMicroservicesJobsDelegate {
	
	@Autowired
	private GetGithubMicroservicesDelegate getGithubMicroservicesDelegate_;
	
	@Autowired
	private PostJenkinsJobDelegate postJenkinsJobDelegate_;
	
	public InitJenkinsMicroservicesJobsResponse initJenkinsMicroservicesJobs(InitJenkinsMicroservicesJobsRequest request) {
		
		// 1. Get GITHUB Microservices
		GetGithubMicroservicesResponse githubMicroservices = getGithubMicroservicesDelegate_.getGithubMicroservices();
		
		// 2. Create Jenkins jobs
		InitJenkinsMicroservicesJobsResponse response = new InitJenkinsMicroservicesJobsResponse();
		
		PostJenkinsJobRequest postJenkinsJobRequest = new PostJenkinsJobRequest();
		postJenkinsJobRequest.setCredentials(request.getJenkinsCredentials());
		postJenkinsJobRequest.setHostDetails(request.getJenkinsHost());
		
		for (TotoMSProject msProject : githubMicroservices.getProjects()) {
			
			System.out.println("Creating a Jenkins job for project " + msProject.getName());
			
			postJenkinsJobRequest.setTotoMsName(msProject.getName());
			
			PostJenkinsJobResponse jobCreationResponse = postJenkinsJobDelegate_.postJenkinsJob(postJenkinsJobRequest);
			
			if (jobCreationResponse.getAlreadyExisting()) response.addJob(msProject.getName());
			else response.addJobCreated(msProject.getName());
		}
		
		response.setNumberOfJobsCreated(response.getJobsCreated().size());
		
		return response;
	}

}
