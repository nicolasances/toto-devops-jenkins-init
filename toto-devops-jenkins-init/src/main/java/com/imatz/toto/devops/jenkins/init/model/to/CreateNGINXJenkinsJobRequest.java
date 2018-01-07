package com.imatz.toto.devops.jenkins.init.model.to;

import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobRequest.Host;
import com.imatz.toto.devops.jenkins.init.util.Credentials;

public class CreateNGINXJenkinsJobRequest {

	/**
	 * Details of the jenkins URL
	 */
	private Host hostDetails_;

	/**
	 * Credentials used to access Jenkins. Username and a token.
	 */
	private Credentials credentials_;

	public Credentials getCredentials() {
		return credentials_;
	}

	public void setCredentials(Credentials credentials) {
		credentials_ = credentials;
	}

	public Host getHostDetails() {
		return hostDetails_;
	}

	public void setHostDetails(Host hostDetails) {
		hostDetails_ = hostDetails;
	}

}
