package no.iktsenteret.pifu.domain.vocab;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ClazzType {
	HOMEROOM("homeroom"), SCHEDULED("scheduled");

	private final String clazzType;

	private ClazzType(String clazzType) {
		this.clazzType = clazzType;
	}

	@JsonValue
	public String getClazzType() {
		return clazzType;
	}
}
