package com.imatz.toto.devops.jenkins.init.model.to;

import java.util.List;

import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobRequest.Host;
import com.imatz.toto.devops.jenkins.init.util.Credentials;

public class SetupNGINXRequest {

	/**
	 * Details of the jenkins URL
	 */
	private Host hostDetails_;

	/**
	 * Credentials used to access Jenkins. Username and a token.
	 */
	private Credentials credentials_;

	private List<String> excludedMicroservices_;

	private Boolean prod_;
	
	/**
	 * Certificate number used in case of prod
	 * The cerificate number is the number that comes after the letsencrypt certificate: 
	 * e.g. fullchain1.pem => certificate number = 1
	 */
	private Integer certificateNum_;
	
	public Integer getCertificateNum() {
		return certificateNum_;
	}
	
	public SetupNGINXRequest() {
		// TODO Auto-generated constructor stub
	}

	public List<String> getExcludedMicroservices() {
		return excludedMicroservices_;
	}

	public void setExcludedMicroservices(List<String> excludedMicroservices) {
		excludedMicroservices_ = excludedMicroservices;
	}

	/**
	 * The name of the toto MS for which to create the job. <br/>
	 * A toto MS name is for example toto-ms-gym
	 */
	private String totoMsName_;

	public void setProd(Boolean prod) {
		prod_ = prod;
	}

	public Boolean getProd() {
		return prod_;
	}

	public String getTotoMsName() {
		return totoMsName_;
	}

	public void setTotoMsName(String totoMsName) {
		totoMsName_ = totoMsName;
	}

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
