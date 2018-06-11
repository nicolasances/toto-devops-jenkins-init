package com.imatz.toto.devops.jenkins.init.model.to;

import java.util.List;

import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobRequest.Host;

public class CreateNGINXConfDelegateRequest {
	/**
	 * Details of the jenkins URL
	 */
	private Host hostDetails_;
	private List<String> excludedMicroservices_;
	private Boolean prod_;
	private Integer certificateNum_;

	public CreateNGINXConfDelegateRequest(Host host, List<String> excludedMicroservices, Boolean prod, Integer certificateNum) {
		excludedMicroservices_ = excludedMicroservices;
		prod_ = prod;
		certificateNum_ = certificateNum;
		hostDetails_ = host;
	}
	
	
	public Host getHostDetails() {
		return hostDetails_;
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
