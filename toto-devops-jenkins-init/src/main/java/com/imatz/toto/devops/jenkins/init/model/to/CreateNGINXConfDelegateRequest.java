package com.imatz.toto.devops.jenkins.init.model.to;

import java.util.List;

public class CreateNGINXConfDelegateRequest {

	private List<String> excludedMicroservices_;
	private Boolean prod_;

	public CreateNGINXConfDelegateRequest(List<String> excludedMicroservices, Boolean prod) {
		excludedMicroservices_ = excludedMicroservices;
		prod_ = prod;
	}

	public Boolean getProd() {
		return prod_;
	}

	public List<String> getExcludedMicroservices() {
		return excludedMicroservices_;
	}

}
