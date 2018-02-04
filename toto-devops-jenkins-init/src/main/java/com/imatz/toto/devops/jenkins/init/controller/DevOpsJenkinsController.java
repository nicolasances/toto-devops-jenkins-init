package com.imatz.toto.devops.jenkins.init.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imatz.toto.devops.jenkins.init.dlg.BuildTotoMicroservicesDelegate;
import com.imatz.toto.devops.jenkins.init.dlg.InitJenkinsMicroservicesJobsDelegate;
import com.imatz.toto.devops.jenkins.init.dlg.kafka.SetupKafkaDelegate;
import com.imatz.toto.devops.jenkins.init.dlg.mongo.SetupMongoDelegate;
import com.imatz.toto.devops.jenkins.init.dlg.nginx.SetupNGINXDelegate;
import com.imatz.toto.devops.jenkins.init.model.to.BuildTotoMicroservicesRequest;
import com.imatz.toto.devops.jenkins.init.model.to.BuildTotoMicroservicesResponse;
import com.imatz.toto.devops.jenkins.init.model.to.InitJenkinsMicroservicesJobsRequest;
import com.imatz.toto.devops.jenkins.init.model.to.InitJenkinsMicroservicesJobsResponse;
import com.imatz.toto.devops.jenkins.init.model.to.SetupKafkaRequest;
import com.imatz.toto.devops.jenkins.init.model.to.SetupKafkaResponse;
import com.imatz.toto.devops.jenkins.init.model.to.SetupMongoRequest;
import com.imatz.toto.devops.jenkins.init.model.to.SetupMongoResponse;
import com.imatz.toto.devops.jenkins.init.model.to.SetupNGINXRequest;
import com.imatz.toto.devops.jenkins.init.model.to.SetupNGINXResponse;

@RestController
public class DevOpsJenkinsController {
	
	@Autowired
	private InitJenkinsMicroservicesJobsDelegate initJenkinsMicroservicesJobsDelegate_;
	
	@Autowired
	private BuildTotoMicroservicesDelegate buildTotoMicroservicesDelegate_;
	
	@Autowired
	private SetupNGINXDelegate setupNGINXDelegate_;
	
	@Autowired
	private SetupMongoDelegate setupMongoDelegate_;
	
	@Autowired
	private SetupKafkaDelegate setupKafkaDelegate_;
	
	@CrossOrigin @RequestMapping(value = "/init", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody InitJenkinsMicroservicesJobsResponse initJenkinsMicroservicesJobs(@RequestBody InitJenkinsMicroservicesJobsRequest request) {
		
		return initJenkinsMicroservicesJobsDelegate_.initJenkinsMicroservicesJobs(request);
	}

	@CrossOrigin @RequestMapping(value = "/build", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BuildTotoMicroservicesResponse buildTotoMicroservices(@RequestBody BuildTotoMicroservicesRequest request) {
		
		return buildTotoMicroservicesDelegate_.buildTotoMicroservices(request);
	}
	
	@CrossOrigin @RequestMapping(value = "/nginx/setup", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody SetupNGINXResponse setupNginx(@RequestBody SetupNGINXRequest request) {
		
		return setupNGINXDelegate_.setupNGINX(request);
	}
	
	@CrossOrigin @RequestMapping(value = "/mongo/setup", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody SetupMongoResponse setupMongo(@RequestBody SetupMongoRequest request) {
		
		return setupMongoDelegate_.setupMongo(request);
	}
	
	@CrossOrigin @RequestMapping(value = "/kafka/setup", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody SetupKafkaResponse setupKafka(@RequestBody SetupKafkaRequest request) {
		
		return setupKafkaDelegate_.setupKafka(request);
	}
}
