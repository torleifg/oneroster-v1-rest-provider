package no.iktsenteret.pifu.web.filters;

import java.time.LocalDate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class CustomSpecification<T> implements Specification<T> {

	private final SearchCriteria criteria;

	public CustomSpecification(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		PathAndValue<T> pathAndValue = new PathAndValue<>(root, criteria);

		final Path<T> path = pathAndValue.resolvePath();
		final Object value = pathAndValue.resolveValue(path);

		switch (criteria.getOperator()) {

		case EQUALS:
			if (value instanceof LocalDate) {
				return builder.equal(path.as(LocalDate.class), value);
			} else if (value instanceof Float) {
				return builder.equal(path.as(Float.class), value);
			} else {
				return builder.equal(builder.lower(path.as(String.class)), value.toString().toLowerCase());
			}

		case CONTAINS:
			return builder.like(builder.lower(path.as(String.class)), "%" + value.toString().toLowerCase() + "%");

		case GREATER_THAN:
			if (value instanceof LocalDate) {
				return builder.greaterThan(path.as(LocalDate.class), (LocalDate) value);
			} else if (value instanceof Float) {
				return builder.greaterThan(path.as(Float.class), (Float) value);
			} else {
				return builder.greaterThan(builder.lower(path.as(String.class)), value.toString().toLowerCase());
			}

		case GREATER_THAN_OR_EQUAL:
			if (value instanceof LocalDate) {
				return builder.greaterThanOrEqualTo(path.as(LocalDate.class), (LocalDate) value);
			} else if (value instanceof Float) {
				return builder.greaterThanOrEqualTo(path.as(Float.class), (Float) value);
			} else {
				return builder.greaterThanOrEqualTo(builder.lower(path.as(String.class)),
						value.toString().toLowerCase());
			}

		case LESSER_THAN:
			if (value instanceof LocalDate) {
				return builder.lessThan(path.as(LocalDate.class), (LocalDate) value);
			} else if (value instanceof Float) {
				return builder.lessThan(path.as(Float.class), (Float) value);
			} else {
				return builder.lessThan(builder.lower(path.as(String.class)), value.toString().toLowerCase());
			}

		case LESSER_THAN_OR_EQUAL:
			if (value instanceof LocalDate) {
				return builder.lessThanOrEqualTo(path.as(LocalDate.class), (LocalDate) value);
			} else if (value instanceof Float) {
				return builder.lessThanOrEqualTo(path.as(Float.class), (Float) value);
			} else {
				return builder.lessThanOrEqualTo(builder.lower(path.as(String.class)), value.toString().toLowerCase());
			}

		case NOT_EQUAL:
			if (value instanceof LocalDate) {
				return builder.notEqual(path.as(LocalDate.class), value);
			} else if (value instanceof Float) {
				return builder.notEqual(path.as(Float.class), value);
			} else {
				return builder.notEqual(builder.lower(path.as(String.class)), value.toString().toLowerCase());
			}

		default:
			return null;
		}
	}
}