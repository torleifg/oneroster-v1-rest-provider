package no.iktsenteret.pifu.web.fields;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import no.iktsenteret.pifu.domain.Base;

public class FieldsHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return FilterProvider.class.equals(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactor) {

		Annotation jsonFilter = AnnotationUtils.getAnnotation(Base.class, JsonFilter.class);

		String filterName = (String) AnnotationUtils.getValue(jsonFilter);

		Set<String> fields = StringUtils
				.commaDelimitedListToSet(StringUtils.trimAllWhitespace(webRequest.getParameter("fields")));

		SimpleBeanPropertyFilter propertyFilter = fields.isEmpty() ? SimpleBeanPropertyFilter.serializeAll()
				: SimpleBeanPropertyFilter.filterOutAllExcept(fields);

		FilterProvider filterProvider = new SimpleFilterProvider().addFilter(filterName, propertyFilter);

		return filterProvider;
	}
}
