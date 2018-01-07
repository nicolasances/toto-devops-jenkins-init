package com.imatz.toto.devops.jenkins.init.model.to;

import java.io.File;

public class CreateNGINXConfDelegateResponse {

	private File nginxConf_;

	public CreateNGINXConfDelegateResponse(File nginxConf) {
		nginxConf_ = nginxConf;
	}

	public File getNginxConf() {
		return nginxConf_;
	}

}
