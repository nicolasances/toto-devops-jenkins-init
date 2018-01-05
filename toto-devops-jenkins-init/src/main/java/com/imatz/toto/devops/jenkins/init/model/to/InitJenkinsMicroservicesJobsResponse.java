package com.imatz.toto.devops.jenkins.init.model.to;

import java.util.ArrayList;
import java.util.List;

public class InitJenkinsMicroservicesJobsResponse {

	private List<String> jobsCreated_ = new ArrayList<String>();
	private Integer numberOfJobsCreated_;

	public void addJobCreated(String job) {
		jobsCreated_.add(job);
	}

	public List<String> getJobsCreated() {
		return jobsCreated_;
	}

	public void setJobsCreated(List<String> jobsCreated) {
		jobsCreated_ = jobsCreated;
	}

	public Integer getNumberOfJobsCreated() {
		return numberOfJobsCreated_;
	}

	public void setNumberOfJobsCreated(Integer numberOfJobsCreated) {
		numberOfJobsCreated_ = numberOfJobsCreated;
	}

}
