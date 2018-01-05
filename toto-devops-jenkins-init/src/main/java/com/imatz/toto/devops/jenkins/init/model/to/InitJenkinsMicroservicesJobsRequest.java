package com.imatz.toto.devops.jenkins.init.model.to;

import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobRequest.Host;
import com.imatz.toto.devops.jenkins.init.util.Credentials;

public class InitJenkinsMicroservicesJobsRequest {

	private Host jenkinsHost_;
	
	/**
	 * As jenkins credentials, user and token must be provided
	 */
	private Credentials jenkinsCredentials_;

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
