package no.iktsenteret.pifu.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.SpringDataWebConfiguration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import no.iktsenteret.pifu.web.fields.FieldsHandlerMethodArgumentResolver;
import no.iktsenteret.pifu.web.filters.FiltersHandlerMethodArgumentResolver;
import no.iktsenteret.pifu.web.pageandsort.CustomPageableHandlerMethodArgumentResolver;

public class WebConfig {

	@Configuration
	public static class PagableConfig extends SpringDataWebConfiguration {

		@Override
		public PageableHandlerMethodArgumentResolver pageableResolver() {
			CustomPageableHandlerMethodArgumentResolver pageableResolver = new CustomPageableHandlerMethodArgumentResolver();
			pageableResolver.setSizeParameterName("limit");
			pageableResolver.setPageParameterName("offset");
			pageableResolver.setFallbackPageable(new PageRequest(0, 100));
			pageableResolver.setMaxPageSize(1000);
			return pageableResolver;
		}
	}

	@Configuration
	public static class FieldsAndFiltersConfig extends WebMvcConfigurerAdapter {

		@Override
		public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
			FieldsHandlerMethodArgumentResolver fieldsResolver = new FieldsHandlerMethodArgumentResolver();
			FiltersHandlerMethodArgumentResolver filtersResolver = new FiltersHandlerMethodArgumentResolver();
			argumentResolvers.addAll(Arrays.asList(fieldsResolver, filtersResolver));
		}
	}
}
