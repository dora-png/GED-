package com.microservice.ged.utils;

public class GroupProfilesBean {
	private Long profile;
	private Long groupe;
	
	public GroupProfilesBean(Long profile, Long groupe) {
		super();
		this.profile = profile;
		this.groupe = groupe;
	}

	public Long getProfile() {
		return profile;
	}

	public void setProfile(Long profile) {
		this.profile = profile;
	}

	public Long getGroupe() {
		return groupe;
	}

	public void setGroupe(Long groupe) {
		this.groupe = groupe;
	}
	
	
	
}
