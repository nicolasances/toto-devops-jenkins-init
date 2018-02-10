package com.imatz.toto.devops.jenkins.init.jenkins;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class JenkinsTotoMSJobDescriptor implements JenkinsJobDescriptor {

	private String totoMsName_;

	public JenkinsTotoMSJobDescriptor(String totoMsName) {
		totoMsName_ = totoMsName;
	}

	public InputStream getConfigFileAsStream() {

		try {

			File templateCopyFile = createTemplateCopy();

			File jenkinsConfigFile = replaceTokens(templateCopyFile);

			return new FileInputStream(jenkinsConfigFile);

		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Replaces the tokens in the template by creating the final config file
	 * 
	 * @param templateCopyFile
	 * @return
	 */
	private File replaceTokens(File templateCopyFile) {
		
		File configFile = null;
		BufferedReader reader = null;
		BufferedWriter writer = null;
		
		try {
			
			configFile = File.createTempFile("jenkins-config-temp", "");
			
			reader = new BufferedReader(new FileReader(templateCopyFile));
			writer = new BufferedWriter(new FileWriter(configFile));
			
			String line;
			while ((line = reader.readLine()) != null) {
				
				writer.write(line.replaceAll("\\$\\{totoMSname\\}", totoMsName_));
				writer.flush();
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			try {
				writer.close();
				reader.close();
			}
			catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		return configFile;
	}

	/**
	 * Creates a copy of the template
	 * 
	 * @return
	 */
	private File createTemplateCopy() {
		
		String templateName = "jenkins-config-template.xml";
		if (totoMsName_.startsWith("toto-nodems-")) templateName = "jenkins-nodems-template.xml";
		if (totoMsName_.startsWith("toto-nodems-docker") || totoMsName_.startsWith("toto-nodems-nginx")) templateName = "jenkins-nodems-docker-template.xml";
		
		InputStream templateIS = this.getClass().getClassLoader().getResourceAsStream(templateName);
		FileOutputStream templateCopyOS = null;

		File templateCopyFile = null;

		try {

			templateCopyFile = File.createTempFile("jenkins-temp", "");

			templateCopyOS = new FileOutputStream(templateCopyFile);

			int b;
			while ((b = templateIS.read()) != -1) {

				templateCopyOS.write(b);
				templateCopyOS.flush();
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			try {
				if (templateIS != null) templateIS.close();
				if (templateCopyOS != null) templateCopyOS.close();
			}
			catch (Exception e2) {
				throw new RuntimeException(e2);
			}
		}

		return templateCopyFile;
	}

}
