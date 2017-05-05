package no.iktsenteret.pifu.web.filters;

public enum SearchOperator {
	EQUALS, NOT_EQUAL, GREATER_THAN, GREATER_THAN_OR_EQUAL, LESSER_THAN, LESSER_THAN_OR_EQUAL, CONTAINS;

	public static final String[] SIMPLE_OPERATOR_SET = { "=", "!=", ">", ">=", "<", "<=", "contains" };

	public static SearchOperator getOperator(String input) {
		switch (input) {
		case "=":
			return EQUALS;
		case "!=":
			return NOT_EQUAL;
		case ">":
			return GREATER_THAN;
		case ">=":
			return GREATER_THAN_OR_EQUAL;
		case "<":
			return LESSER_THAN;
		case "<=":
			return LESSER_THAN_OR_EQUAL;
		case "contains":
			return CONTAINS;
		default:
			return null;
		}
	}
}