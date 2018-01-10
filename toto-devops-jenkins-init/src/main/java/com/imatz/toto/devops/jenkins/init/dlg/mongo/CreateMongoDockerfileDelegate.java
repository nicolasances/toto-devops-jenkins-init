package com.imatz.toto.devops.jenkins.init.dlg.mongo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class CreateMongoDockerfileDelegate {

	public static String TARGET_FOLDER_NAME = "/mongo-setup";

	public void createMongoDockerfile() {

		// 1. Create dir if it doesn't exist
		createDir();

		// 2. Create docker file
		File dockerfile = new File("/" + TARGET_FOLDER_NAME + "/Dockerfile");

		// 3. Fill docker file
		createDockerfile(dockerfile);

	}

	/**
	 * Creates the Dockerfile for the nginx proxy
	 * 
	 * @param dockerfile
	 */
	private void createDockerfile(File dockerfile) {

		BufferedWriter writer = null;

		try {

			writer = new BufferedWriter(new FileWriter(dockerfile));

			writer.write("FROM mongo:3.0");
			writer.newLine();
			writer.newLine();
			writer.write("CMD [\"mongod\", \"--smallfiles\"]");
			writer.newLine();

			writer.flush();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if (writer != null) try {
				writer.close();
			}
			catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Creates the target dir for the nginx Dockerfile
	 */
	private File createDir() {

		File dir = new File("/" + TARGET_FOLDER_NAME);

		if (!dir.exists()) dir.mkdir();

		return dir;
	}
}
