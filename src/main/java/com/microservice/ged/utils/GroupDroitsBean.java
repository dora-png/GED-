package com.microservice.ged.utils;

public class GroupDroitsBean {
	private Long droit;
	private Long groupe;
	
	public GroupDroitsBean(Long droit, Long groupe) {
		super();
		this.droit = droit;
		this.groupe = groupe;
	}

	public Long getDroit() {
		return droit;
	}

	public void setDroit(Long droit) {
		this.droit = droit;
	}

	public Long getGroupe() {
		return groupe;
	}

	public void setGroupe(Long groupe) {
		this.groupe = groupe;
	}
	
	

}
