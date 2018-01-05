package com.imatz.toto.devops.jenkins.init.dlg;

import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.imatz.toto.devops.jenkins.init.model.to.GetGithubMicroservicesResponse;
import com.imatz.toto.devops.jenkins.init.model.to.GetGithubMicroservicesResponse.TotoMSProject;
import com.imatz.toto.devops.jenkins.init.util.HTTPCall;
import com.mongodb.util.JSON;

/**
 * Returns the list of github stored toto microserices project
 * @author nicolas
 *
 */
@Service
public class GetGithubMicroservicesDelegate {
	
	public GetGithubMicroservicesResponse getGithubMicroservices() {
		
		// 1. Get from git hub
		HTTPCall call = new HTTPCall("https://api.github.com/users/nicolasances/repos");

		String result = call.call(null, "GET", "application/json", "application/json");
		
		// 2. Parse result
		@SuppressWarnings("unchecked")
		List<Document> docs = (List<Document>) JSON.parse(result);
		
		// 3. Transform
		GetGithubMicroservicesResponse response = new GetGithubMicroservicesResponse();
		
		for (Document doc : docs) {
			
			if (doc.getString("name").startsWith("toto-ms-")) response.addProject(new TotoMSProject(doc.getString("name"), doc.getString("clone_url")));
		}
		
		return response;
	}
	
}
