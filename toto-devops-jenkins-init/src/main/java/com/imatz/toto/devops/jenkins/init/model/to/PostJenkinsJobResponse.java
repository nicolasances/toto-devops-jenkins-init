package com.imatz.toto.devops.jenkins.init.model.to;

public class PostJenkinsJobResponse {

	private Boolean successfull_;
	private Boolean alreadyExisting_;

	public Boolean getAlreadyExisting() {
		return alreadyExisting_;
	}

	public void setAlreadyExisting(Boolean alreadyExisting) {
		alreadyExisting_ = alreadyExisting;
	}

	public Boolean getSuccessfull() {
		return successfull_;
	}

	public void setSuccessfull(Boolean successfull) {
		successfull_ = successfull;
	}

}
