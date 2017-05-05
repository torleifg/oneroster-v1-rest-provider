package no.iktsenteret.pifu.domain.vocab;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AcademicSessionType {
	TERM("term"), GRADINGPERIOD("gradingPeriod"), SCHOOLYEAR("schoolyear"), SEMESTER("semester");

	private final String academicSessionType;

	private AcademicSessionType(String academicSessionType) {
		this.academicSessionType = academicSessionType;
	}

	@JsonValue
	public String getAcademicSessionType() {
		return academicSessionType;
	}
}
