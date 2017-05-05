package no.iktsenteret.pifu.domain.vocab;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
	ACTIVE("active"), INACTIVE("inactive"), TOBEDELETED("tobedeleted");

	private final String status;

	private Status(String status) {
		this.status = status;
	}

	@JsonValue
	public String getStatus() {
		return status;
	}
}
