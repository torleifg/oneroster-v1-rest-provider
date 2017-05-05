package no.iktsenteret.pifu.domain.vocab;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
	TEACHER("teacher"), STUDENT("student"), PARENT("parent"), GUARDIAN("guardian"), RELATIVE("relative"), AIDE(
			"aide"), ADMINISTRATOR("administrator");

	private final String role;

	private Role(String role) {
		this.role = role;
	}

	@JsonValue
	public String getRole() {
		return role;
	}
}
