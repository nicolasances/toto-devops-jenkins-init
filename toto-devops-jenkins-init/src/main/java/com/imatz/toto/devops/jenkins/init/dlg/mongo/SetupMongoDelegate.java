package com.imatz.toto.devops.jenkins.init.dlg.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imatz.toto.devops.jenkins.init.model.to.CreateMongoJenkinsJobRequest;
import com.imatz.toto.devops.jenkins.init.model.to.RunMongoJenkinsJobRequest;
import com.imatz.toto.devops.jenkins.init.model.to.SetupMongoRequest;
import com.imatz.toto.devops.jenkins.init.model.to.SetupMongoResponse;

@Service
public class SetupMongoDelegate {
	
	@Autowired
	private CreateMongoDockerfileDelegate createMongoDockerfileDelegate_;
	
	@Autowired
	private CreateMongoJenkinsJobDelegate createMongoJenkinsJobDelegate_;
	
	@Autowired
	private RunMongoJenkinsJobDelegate runMongoJenkinsJobDelegate_;

	public SetupMongoResponse setupMongo (SetupMongoRequest request) {
		
		createMongoDockerfileDelegate_.createMongoDockerfile();
		
		CreateMongoJenkinsJobRequest createNginxJenkinsJobRequest = new CreateMongoJenkinsJobRequest();
		createNginxJenkinsJobRequest.setCredentials(request.getCredentials());
		createNginxJenkinsJobRequest.setHostDetails(request.getHostDetails());
		
		createMongoJenkinsJobDelegate_.createMongoJenkinsJob(createNginxJenkinsJobRequest);
		
		RunMongoJenkinsJobRequest runNginxJenkinsJobRequest = new RunMongoJenkinsJobRequest();
		runNginxJenkinsJobRequest.setCredentials(request.getCredentials());
		runNginxJenkinsJobRequest.setHostDetails(request.getHostDetails());
		
		runMongoJenkinsJobDelegate_.runMongoJenkinsJob(runNginxJenkinsJobRequest);
		
		return new SetupMongoResponse();
		
	}
}
