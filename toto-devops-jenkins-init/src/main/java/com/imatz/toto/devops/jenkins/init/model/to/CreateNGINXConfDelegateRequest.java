package com.imatz.toto.devops.jenkins.init.model.to;

import java.util.List;

public class CreateNGINXConfDelegateRequest {

	private List<String> excludedMicroservices_;

	public CreateNGINXConfDelegateRequest(List<String> excludedMicroservices) {
		excludedMicroservices_ = excludedMicroservices;
	}

	public List<String> getExcludedMicroservices() {
		return excludedMicroservices_;
	}

}
