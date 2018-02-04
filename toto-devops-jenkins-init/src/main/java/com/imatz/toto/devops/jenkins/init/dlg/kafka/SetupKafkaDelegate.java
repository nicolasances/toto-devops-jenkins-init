package com.imatz.toto.devops.jenkins.init.dlg.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imatz.toto.devops.jenkins.init.model.to.CreateKafkaJenkinsJobRequest;
import com.imatz.toto.devops.jenkins.init.model.to.RunKafkaJenkinsJobRequest;
import com.imatz.toto.devops.jenkins.init.model.to.SetupKafkaRequest;
import com.imatz.toto.devops.jenkins.init.model.to.SetupKafkaResponse;

@Service
public class SetupKafkaDelegate {
	
	@Autowired
	private CreateKafkaJenkinsJobDelegate createKafkaJenkinsJobDelegate_;
	
	@Autowired
	private RunKafkaJenkinsJobDelegate runKafkaJenkinsJobDelegate_;

	public SetupKafkaResponse setupKafka (SetupKafkaRequest request) {
		
		CreateKafkaJenkinsJobRequest createKafkaJenkinsJobRequest = new CreateKafkaJenkinsJobRequest();
		createKafkaJenkinsJobRequest.setCredentials(request.getCredentials());
		createKafkaJenkinsJobRequest.setHostDetails(request.getHostDetails());
		
		createKafkaJenkinsJobDelegate_.createKafkaJenkinsJob(createKafkaJenkinsJobRequest);
		
		RunKafkaJenkinsJobRequest runKafkaJenkinsJobRequest = new RunKafkaJenkinsJobRequest();
		runKafkaJenkinsJobRequest.setCredentials(request.getCredentials());
		runKafkaJenkinsJobRequest.setHostDetails(request.getHostDetails());
		
		runKafkaJenkinsJobDelegate_.runKafkaJenkinsJob(runKafkaJenkinsJobRequest);
		
		return new SetupKafkaResponse();
		
	}
}
