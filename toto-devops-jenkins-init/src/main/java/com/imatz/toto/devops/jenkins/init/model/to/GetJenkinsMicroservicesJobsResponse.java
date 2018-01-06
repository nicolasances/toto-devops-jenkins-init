package com.imatz.toto.devops.jenkins.init.model.to;

import java.util.ArrayList;
import java.util.List;

public class GetJenkinsMicroservicesJobsResponse {

	private List<String> jobs_ = new ArrayList<String>();

	public List<String> getJobs() {
		return jobs_;
	}

	public void setJobs(List<String> jobs) {
		jobs_ = jobs;
	}

	public void addJob(String job) {
		jobs_.add(job);
	}

}
