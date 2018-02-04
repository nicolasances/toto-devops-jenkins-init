package com.imatz.toto.devops.jenkins.init.dlg.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imatz.toto.devops.jenkins.init.dlg.PostJenkinsJobStartDelegate;
import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobStartRequest;
import com.imatz.toto.devops.jenkins.init.model.to.RunKafkaJenkinsJobRequest;
import com.imatz.toto.devops.jenkins.init.model.to.RunKafkaJenkinsJobResponse;

@Service
public class RunKafkaJenkinsJobDelegate {
	
	@Autowired
	private PostJenkinsJobStartDelegate postJenkinsJobStartDelegate_;
	
	public RunKafkaJenkinsJobResponse runKafkaJenkinsJob (RunKafkaJenkinsJobRequest request) {
		
		PostJenkinsJobStartRequest req = new PostJenkinsJobStartRequest();
		req.setJenkinsCredentials(request.getCredentials());
		req.setJenkinsHost(request.getHostDetails());
		req.setJobName(CreateKafkaJenkinsJobDelegate.KAFKA_JENKINS_JOB_NAME);
		
		postJenkinsJobStartDelegate_.postJenkinsJobStart(req);
		
		return new RunKafkaJenkinsJobResponse();
	}

}
