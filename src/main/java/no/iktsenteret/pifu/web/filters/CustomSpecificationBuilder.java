package no.iktsenteret.pifu.web.filters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

public class CustomSpecificationBuilder<T> {

	private static final LogicalOperator DEFAULT_LOGICAL_OPERATOR = LogicalOperator.NONE;

	private LogicalOperator logicalOperator = DEFAULT_LOGICAL_OPERATOR;

	private final List<SearchCriteria> params;

	public CustomSpecificationBuilder() {
		params = new ArrayList<SearchCriteria>();
	}

	public void setLogicalOperator(LogicalOperator logicalOperator) {
		this.logicalOperator = logicalOperator;
	}

	public CustomSpecificationBuilder<T> with(String key, SearchOperator operation, String value) {
		params.add(new SearchCriteria(key, operation, value));
		return this;
	}

	public Specification<T> build() {
		if (params.size() == 0) {
			return null;
		}

		List<Specification<T>> specs = new ArrayList<Specification<T>>();
		for (SearchCriteria param : params) {
			specs.add(new CustomSpecification<T>(param));
		}

		Specifications<T> result = Specifications.where(specs.get(0));

		if (logicalOperator.isAnd())
			return result.and(specs.get(1));
		else if (logicalOperator.isOr())
			return result.or(specs.get(1));
		else
			return result;
	}
}
