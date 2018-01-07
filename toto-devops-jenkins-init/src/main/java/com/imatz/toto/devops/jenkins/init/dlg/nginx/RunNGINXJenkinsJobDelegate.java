package com.imatz.toto.devops.jenkins.init.dlg.nginx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imatz.toto.devops.jenkins.init.dlg.PostJenkinsJobStartDelegate;
import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobStartRequest;
import com.imatz.toto.devops.jenkins.init.model.to.RunNGINXJenkinsJobRequest;
import com.imatz.toto.devops.jenkins.init.model.to.RunNGINXJenkinsJobResponse;

@Service
public class RunNGINXJenkinsJobDelegate {
	
	@Autowired
	private PostJenkinsJobStartDelegate postJenkinsJobStartDelegate_;
	
	public RunNGINXJenkinsJobResponse runNGINXJenkinsJob (RunNGINXJenkinsJobRequest request) {
		
		PostJenkinsJobStartRequest req = new PostJenkinsJobStartRequest();
		req.setJenkinsCredentials(request.getCredentials());
		req.setJenkinsHost(request.getHostDetails());
		req.setJobName(CreateNGINXJenkinsJobDelegate.NGINX_JENKINS_JOB_NAME);
		
		postJenkinsJobStartDelegate_.postJenkinsJobStart(req);
		
		return new RunNGINXJenkinsJobResponse();
	}

}
