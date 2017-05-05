package no.iktsenteret.pifu.domain.vocab;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrgType {
	SCHOOL("school"), LOCAL("local"), STATE("state"), NATIONAL("national");

	private final String orgType;

	private OrgType(String orgType) {
		this.orgType = orgType;
	}

	@JsonValue
	public String getOrgType() {
		return orgType;
	}
}
