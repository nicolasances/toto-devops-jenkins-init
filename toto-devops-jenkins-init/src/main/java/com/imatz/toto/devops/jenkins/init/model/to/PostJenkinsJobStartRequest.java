package com.imatz.toto.devops.jenkins.init.model.to;

import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobRequest.Host;
import com.imatz.toto.devops.jenkins.init.util.Credentials;

public class PostJenkinsJobStartRequest {

	private Host jenkinsHost_;
	private Credentials jenkinsCredentials_;
	private String jobName_;

	public String getJobName() {
		return jobName_;
	}

	public void setJobName(String jobName) {
		jobName_ = jobName;
	}

	public Credentials getJenkinsCredentials() {
		return jenkinsCredentials_;
	}

	public void setJenkinsCredentials(Credentials jenkinsCredentials) {
		jenkinsCredentials_ = jenkinsCredentials;
	}

	public Host getJenkinsHost() {
		return jenkinsHost_;
	}

	public void setJenkinsHost(Host jenkinsHost) {
		jenkinsHost_ = jenkinsHost;
	}

}
