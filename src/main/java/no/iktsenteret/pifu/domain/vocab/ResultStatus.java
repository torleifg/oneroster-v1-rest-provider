package no.iktsenteret.pifu.domain.vocab;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResultStatus {
	NOTSUBMITTED("not submitted"), SUBMITTED("submitted"), PARTIALLYGRADED("partially graded"), FULLYGRADED(
			"fully graded"), EXEMPT("exempt");

	private final String resultStatus;

	private ResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	@JsonValue
	public String getResultStatus() {
		return resultStatus;
	}
}
