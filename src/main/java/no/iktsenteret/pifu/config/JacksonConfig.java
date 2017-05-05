package no.iktsenteret.pifu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Configuration
public class JacksonConfig {

	@Bean
	public MappingJackson2HttpMessageConverter Converter(final ObjectMapper objectMapper) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setDefaultCharset(null);
		objectMapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));
		converter.setObjectMapper(objectMapper);
		return converter;
	}
}
