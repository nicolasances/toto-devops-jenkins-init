package com.imatz.toto.devops.jenkins.init.model.to;

import java.io.File;

public class CreateNGINXDockerfileResponse {

	private File dockerfile_;

	public CreateNGINXDockerfileResponse(File dockerfile) {
		dockerfile_ = dockerfile;
	}

	public File getDockerfile() {
		return dockerfile_;
	}

}
