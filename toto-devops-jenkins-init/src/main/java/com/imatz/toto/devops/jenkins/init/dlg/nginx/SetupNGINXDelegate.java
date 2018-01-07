package com.imatz.toto.devops.jenkins.init.dlg.nginx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imatz.toto.devops.jenkins.init.model.to.CreateNGINXConfDelegateRequest;
import com.imatz.toto.devops.jenkins.init.model.to.CreateNGINXDockerfileRequest;
import com.imatz.toto.devops.jenkins.init.model.to.CreateNGINXJenkinsJobRequest;
import com.imatz.toto.devops.jenkins.init.model.to.RunNGINXJenkinsJobRequest;
import com.imatz.toto.devops.jenkins.init.model.to.SetupNGINXRequest;
import com.imatz.toto.devops.jenkins.init.model.to.SetupNGINXResponse;

@Service
public class SetupNGINXDelegate {
	
	@Autowired
	private CreateNGINXConfDelegate createNGINXConfDelegate_;
	
	@Autowired
	private CreateNGINXDockerfileDelegate createNGINXDockerfileDelegate_;
	
	@Autowired
	private CreateNGINXJenkinsJobDelegate createNGINXJenkinsJobDelegate_;
	
	@Autowired
	private RunNGINXJenkinsJobDelegate runNGINXJenkinsJobDelegate_;

	public SetupNGINXResponse setupNGINX (SetupNGINXRequest request) {
		
		createNGINXConfDelegate_.createNGINXConfDelegate(new CreateNGINXConfDelegateRequest(request.getExcludedMicroservices()));
		
		createNGINXDockerfileDelegate_.createNGINXDockerfile(new CreateNGINXDockerfileRequest());
		
		CreateNGINXJenkinsJobRequest createNginxJenkinsJobRequest = new CreateNGINXJenkinsJobRequest();
		createNginxJenkinsJobRequest.setCredentials(request.getCredentials());
		createNginxJenkinsJobRequest.setHostDetails(request.getHostDetails());
		
		createNGINXJenkinsJobDelegate_.createNGINXJenkinsJob(createNginxJenkinsJobRequest);
		
		RunNGINXJenkinsJobRequest runNginxJenkinsJobRequest = new RunNGINXJenkinsJobRequest();
		runNginxJenkinsJobRequest.setCredentials(request.getCredentials());
		runNginxJenkinsJobRequest.setHostDetails(request.getHostDetails());
		
		runNGINXJenkinsJobDelegate_.runNGINXJenkinsJob(runNginxJenkinsJobRequest);
		
		return new SetupNGINXResponse();
		
	}
}
