package no.iktsenteret.pifu.web;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import no.iktsenteret.pifu.web.exception.NotFoundException;

@RestController
public class BaseController {

	private final RequestMappingHandlerMapping handlerMapping;

	public BaseController(RequestMappingHandlerMapping handlerMapping) {
		this.handlerMapping = handlerMapping;
	}

	@GetMapping(value = "/")
	public Map<String, List<String>> defaultMethod() {
		Set<RequestMappingInfo> keySet = handlerMapping.getHandlerMethods().keySet();

		List<String> endpoints = keySet.stream().flatMap(key -> key.getPatternsCondition().getPatterns().stream())
				.filter(pattern -> pattern.matches("\\/(?!error)\\w+.*")).sorted().collect(Collectors.toList());

		return Collections.singletonMap("endpoints", endpoints);
	}

	@GetMapping(value = "/**")
	public void fallbackMethod() {
		throw new NotFoundException("Non-existent Endpoint");
	}
}
