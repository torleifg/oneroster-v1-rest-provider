package no.iktsenteret.pifu.web.filters;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.util.TypeUtils;

import no.iktsenteret.pifu.domain.Base;
import no.iktsenteret.pifu.web.exception.BadRequestException;

public class PathAndValue<T> {

	private final Root<T> root;
	private final SearchCriteria criteria;

	public PathAndValue(Root<T> root, SearchCriteria criteria) {
		this.root = root;
		this.criteria = criteria;
	}

	public Path<T> resolvePath() {
		if (criteria.getKey().contains(".")) {
			return nestedPath();
		} else {
			return simplePath();
		}
	}

	public Object resolveValue(final Path<T> path) {
		Class<?> type = path.getJavaType();

		if (type.equals(ZonedDateTime.class) || type.equals(LocalDate.class)) {
			try {
				return LocalDate.parse(criteria.getValue());
			} catch (DateTimeParseException e) {
				throw new BadRequestException("Invalid date format (YYYY-MM-DD): " + e.getParsedString());
			}

		} else if (type.equals(Float.class)) {
			try {
				return Float.parseFloat(criteria.getValue());
			} catch (NumberFormatException e) {
				throw new BadRequestException("Invalid number format: " + e.getMessage());
			}

		} else {
			return criteria.getValue();
		}
	}

	private Path<T> nestedPath() {
		String[] nestedPath = criteria.getKey().split("\\.");
		Class<?> type = getType(nestedPath[0]);

		try {
			if (type.equals(Map.class)) {
				return root.join(nestedPath[0]);
			} else {
				return root.join(nestedPath[0]).get(nestedPath[1]);
			}
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	private Path<T> simplePath() {
		Class<?> type = getType(criteria.getKey());

		if (TypeUtils.isAssignable(Base.class, type) || type.equals(Set.class) || type.equals(Map.class)) {
			throw new BadRequestException("Dot notation must be applied to nested objects: " + criteria.getKey());
		}

		try {
			if (type.equals(List.class)) {
				return root.join(criteria.getKey());
			} else {
				return root.get(criteria.getKey());
			}
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	private Class<?> getType(String key) {
		try {
			return root.get(key).getJavaType();
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}
}