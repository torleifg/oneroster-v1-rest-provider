package no.iktsenteret.pifu.domain;

import javax.persistence.Embeddable;

@Embeddable
public class ResultValue {

	private Float min, max;

	protected ResultValue() {
	}

	public ResultValue(Float min, Float max) {
		this.min = min;
		this.max = max;
	}

	public Float getMin() {
		return min;
	}

	public void setMin(Float min) {
		this.min = min;
	}

	public Float getMax() {
		return max;
	}

	public void setMax(Float max) {
		this.max = max;
	}
}
