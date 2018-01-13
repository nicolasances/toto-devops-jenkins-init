package com.imatz.toto.devops.jenkins.init.dlg;

import java.io.IOException;
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.imatz.toto.devops.jenkins.init.model.to.GetJenkinsMicroservicesJobsRequest;
import com.imatz.toto.devops.jenkins.init.model.to.GetJenkinsMicroservicesJobsResponse;
import com.imatz.toto.devops.jenkins.init.model.to.PostJenkinsJobRequest.Host;
import com.imatz.toto.devops.jenkins.init.util.HTTPCall;

@Service
public class GetJenkinsMicroservicesJobsDelegate {
	
	@SuppressWarnings("unchecked")
	public GetJenkinsMicroservicesJobsResponse getJenkinsMicroservicesJobs(GetJenkinsMicroservicesJobsRequest request) {
		
		try {
			
			HTTPCall call = new HTTPCall(buildURL(request.getJenkinsHost()));
			call.setCredentials(request.getJenkinsCredentials());
			
			String result = call.call(null, "GET", "application/json", "application/json");
			
			Document resultObject = Document.parse(result);
			
			GetJenkinsMicroservicesJobsResponse response = new GetJenkinsMicroservicesJobsResponse();
			
			for (Document doc : (List<Document>) resultObject.get("jobs")) {
				
				String job = doc.getString("name");
				
				if (job.startsWith("build-toto-ms-") || job.startsWith("build-toto-nodems-")) response.addJob(job);
			}
			
			return response;
			
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * Builds the Jenkins URL to retrieve the list of jobs
	 * 
	 * @param hostDetails
	 * @return
	 */
	private String buildURL(Host hostDetails) {

		return hostDetails.getProtocol() + "://" + hostDetails.getHost() + ":" + hostDetails.getPort() + "/api/json?tree=jobs[name]";

	}

}
