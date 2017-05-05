package no.iktsenteret.pifu.web.filters;

public enum LogicalOperator {
	AND, OR, NONE;

	public boolean isAnd() {
		return this.equals(AND);
	}

	public boolean isOr() {
		return this.equals(OR);
	}

	public static LogicalOperator fromString(String value) {
		try {
			return LogicalOperator.valueOf(value.toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException(String.format(
					"Invalid value '%s' for LogicalOperator given! Has to be either 'AND' or 'OR' (case sensitive).",
					value), e);
		}
	}
}
