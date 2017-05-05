package no.iktsenteret.pifu.web.filters;

public class SearchCriteria {

	private final String key;
	private final SearchOperator operator;
	private final String value;

	public SearchCriteria(String key, SearchOperator operator, String value) {
		this.key = key;
		this.operator = operator;
		this.value = value;
	}

	public String getKey() {
		return key.replace("class", "clazz").replace("primary", "pr1ma4ry");
	}

	public SearchOperator getOperator() {
		return operator;
	}

	public String getValue() {
		return value.replaceAll("\\'", "");
	}
}
