package no.iktsenteret.pifu.web.filters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.MethodParameter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import no.iktsenteret.pifu.domain.Base;
import no.iktsenteret.pifu.web.exception.BadRequestException;

public class FiltersHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	private static final Pattern SIMPLE_PATTERN = Pattern.compile(
			"(\\w+(?:\\.\\w+)?)(" + String.join("|", SearchOperator.SIMPLE_OPERATOR_SET) + ")(\\'[\\S ]+?\\')");

	private static final Pattern LOGICAL_PATTERN = Pattern.compile(SIMPLE_PATTERN + " (AND|OR) " + SIMPLE_PATTERN);

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return Specification.class.equals(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

		String filter = webRequest.getParameter("filter");

		Specification<? extends Base> spec = StringUtils.hasText(filter) ? buildSpecification(filter) : null;

		return spec;
	}

	private static Specification<? extends Base> buildSpecification(String filter) {
		CustomSpecificationBuilder<? extends Base> builder = new CustomSpecificationBuilder<>();

		Matcher logicalMatcher = LOGICAL_PATTERN.matcher(filter);
		Matcher simpleMatcher = SIMPLE_PATTERN.matcher(filter);

		if (logicalMatcher.matches()) {
			builder.setLogicalOperator(LogicalOperator.fromString(logicalMatcher.group(4)));
			while (simpleMatcher.find()) {
				builder.with(simpleMatcher.group(1), SearchOperator.getOperator(simpleMatcher.group(2)),
						simpleMatcher.group(3));
			}

		} else if (simpleMatcher.matches()) {
			builder.with(simpleMatcher.group(1), SearchOperator.getOperator(simpleMatcher.group(2)),
					simpleMatcher.group(3));

		} else {
			throw new BadRequestException("Invalid filter expression");
		}
		return builder.build();
	}
}