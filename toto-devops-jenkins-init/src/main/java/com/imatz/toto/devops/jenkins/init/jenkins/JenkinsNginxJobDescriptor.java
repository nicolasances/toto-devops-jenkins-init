package com.imatz.toto.devops.jenkins.init.jenkins;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class JenkinsNginxJobDescriptor implements JenkinsJobDescriptor {

	public InputStream getConfigFileAsStream() {

		try {

			File templateCopyFile = createTemplateCopy();

			return new FileInputStream(templateCopyFile);

		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Creates a copy of the template
	 * 
	 * @return
	 */
	private File createTemplateCopy() {

		InputStream templateIS = this.getClass().getClassLoader().getResourceAsStream("jenkins-nginx-job-pipeline.xml");
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
