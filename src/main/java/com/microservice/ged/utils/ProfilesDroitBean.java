package com.microservice.ged.utils;

public class ProfilesDroitBean {
	private Long droit;
	private Long profile;
	
	public ProfilesDroitBean(Long droit, Long profile) {
		super();
		this.droit = droit;
		this.profile = profile;
	}

	public Long getDroit() {
		return droit;
	}

	public void setDroit(Long droit) {
		this.droit = droit;
	}

	public Long getProfile() {
		return profile;
	}

	public void setProfile(Long profile) {
		this.profile = profile;
	}
	
	
	

}
