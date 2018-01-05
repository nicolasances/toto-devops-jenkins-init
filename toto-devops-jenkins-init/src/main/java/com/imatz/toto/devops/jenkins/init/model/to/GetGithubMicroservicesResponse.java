package com.imatz.toto.devops.jenkins.init.model.to;

import java.util.ArrayList;
import java.util.List;

public class GetGithubMicroservicesResponse {

	private List<TotoMSProject> projects_ = new ArrayList<GetGithubMicroservicesResponse.TotoMSProject>();

	public List<TotoMSProject> getProjects() {
		return projects_;
	}

	public void setProjects(List<TotoMSProject> projects) {
		projects_ = projects;
	}

	public void addProject(TotoMSProject project) {

		projects_.add(project);
	}

	public static class TotoMSProject {

		private String name_;
		private String repository_;

		public TotoMSProject(String name, String repositoryUrl) {
			setName(name);
			setRepository(repositoryUrl);
		}

		public String getRepository() {
			return repository_;
		}

		public void setRepository(String repository) {
			repository_ = repository;
		}

		public String getName() {
			return name_;
		}

		public void setName(String name) {
			name_ = name;
		}
	}

}
