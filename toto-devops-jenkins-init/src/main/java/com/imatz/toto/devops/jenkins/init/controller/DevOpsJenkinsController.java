package com.imatz.toto.devops.jenkins.init.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imatz.toto.devops.jenkins.init.dlg.InitJenkinsMicroservicesJobsDelegate;
import com.imatz.toto.devops.jenkins.init.model.to.InitJenkinsMicroservicesJobsRequest;
import com.imatz.toto.devops.jenkins.init.model.to.InitJenkinsMicroservicesJobsResponse;

@RestController
public class DevOpsJenkinsController {
	
	@Autowired
	private InitJenkinsMicroservicesJobsDelegate initJenkinsMicroservicesJobsDelegate_;
	
	@RequestMapping(value = "/init", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody InitJenkinsMicroservicesJobsResponse initJenkinsMicroservicesJobs(@RequestBody InitJenkinsMicroservicesJobsRequest request) {
		
		return initJenkinsMicroservicesJobsDelegate_.initJenkinsMicroservicesJobs(request);
	}

}
