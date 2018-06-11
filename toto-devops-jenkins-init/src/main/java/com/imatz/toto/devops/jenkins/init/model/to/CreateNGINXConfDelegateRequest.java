package com.imatz.toto.devops.jenkins.init.model.to;

import java.util.List;

public class CreateNGINXConfDelegateRequest {

	private List<String> excludedMicroservices_;
	private Boolean prod_;
	private Integer certificateNum_;

	public CreateNGINXConfDelegateRequest(List<String> excludedMicroservices, Boolean prod, Integer certificateNum) {
		excludedMicroservices_ = excludedMicroservices;
		prod_ = prod;
		certificateNum_ = certificateNum;
	}
	
	public Integer getCertificateNum() {
		return certificateNum_;
	}

	public Boolean getProd() {
		return prod_;
	}

	public List<String> getExcludedMicroservices() {
		return excludedMicroservices_;
	}

}
