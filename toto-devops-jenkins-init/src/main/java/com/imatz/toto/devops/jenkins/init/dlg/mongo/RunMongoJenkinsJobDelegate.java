package com.imatz.toto.devops.jenkins.init.dlg.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imatz.toto.devops.jenkins.init.dlg.PostJenkinsJobStartDelegate;
import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobStartRequest;
import com.imatz.toto.devops.jenkins.init.model.to.RunMongoJenkinsJobRequest;

@Service
public class RunMongoJenkinsJobDelegate {
	
	@Autowired
	private PostJenkinsJobStartDelegate postJenkinsJobStartDelegate_;
	
	public void runMongoJenkinsJob (RunMongoJenkinsJobRequest request) {
		
		PostJenkinsJobStartRequest req = new PostJenkinsJobStartRequest();
		req.setJenkinsCredentials(request.getCredentials());
		req.setJenkinsHost(request.getHostDetails());
		req.setJobName(CreateMongoJenkinsJobDelegate.MONGO_JENKINS_JOB_NAME);
		
		postJenkinsJobStartDelegate_.postJenkinsJobStart(req);
		
	}

}
