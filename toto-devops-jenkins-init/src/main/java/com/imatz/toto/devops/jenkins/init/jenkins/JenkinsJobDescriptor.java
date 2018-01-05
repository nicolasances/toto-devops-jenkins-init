package com.imatz.toto.devops.jenkins.init.jenkins;

import java.io.InputStream;

/**
 * Describes a configuration of the Jenkins Job
 * 
 * @author nicolas
 *
 */
public interface JenkinsJobDescriptor {

	/**
	 * Retrieves the Jenkins configuration file and returns it as an inputStream
	 * 
	 * @return
	 */
	public InputStream getConfigFileAsStream();

}
