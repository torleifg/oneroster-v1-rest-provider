package no.iktsenteret.pifu.web.response;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RelProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;

import no.iktsenteret.pifu.domain.Base;

@Component
public class ApiResponse {

	private final RelProvider relProvider;

	private final EntityLinks entityLinks;

	public ApiResponse(RelProvider relProvider, EntityLinks entityLinks) {
		this.relProvider = relProvider;
		this.entityLinks = entityLinks;
	}

	public <T extends Base> MappingJacksonValue item(Class<T> clazz, T item, FilterProvider fields) {
		Map<String, T> itemMap = Collections.singletonMap(relProvider.getItemResourceRelFor(clazz), item);

		MappingJacksonValue value = new MappingJacksonValue(itemMap);
		value.setFilters(fields);

		return value;
	}

	public <T extends Base> MappingJacksonValue collection(Class<T> clazz, Page<T> collection, FilterProvider fields) {
		Map<String, List<T>> itemMap = Collections.singletonMap(relProvider.getCollectionResourceRelFor(clazz),
				collection.getContent());

		MappingJacksonValue value = new MappingJacksonValue(itemMap);
		value.setFilters(fields);

		return value;
	}

	public <T extends Base> HttpHeaders httpHeaders(HttpServletRequest request, Page<T> entities, Pageable page) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Total-Count", String.valueOf(entities.getTotalElements()));

		int limit = page.next().getPageSize();
		int offset = page.next().getPageNumber();
		String next = "<" + generateUri(request, limit, offset) + ">; rel=\"next\"";

		limit = page.previousOrFirst().getPageSize();
		offset = page.previousOrFirst().getPageNumber();
		String prev = "<" + generateUri(request, limit, offset) + ">; rel=\"prev\"";

		limit = page.first().getPageSize();
		offset = page.first().getPageNumber();
		String first = "<" + generateUri(request, limit, offset) + ">; rel=\"first\"";

		limit = (int) entities.getTotalElements() % page.getPageSize();
		offset = (int) entities.getTotalElements() - limit;
		String last = "<" + generateUri(request, limit, offset) + ">; rel=\"last\"";

		headers.add(HttpHeaders.LINK, String.join(",", Arrays.asList(next, prev, first, last)));

		return headers;
	}

	private String generateUri(HttpServletRequest request, int limit, int offset) {
		return UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString()).queryParam("limit", limit)
				.queryParam("offset", offset).toUriString();
	}

	public <T extends Base> Link linkFor(T entity) {
		Link link = entityLinks.linkFor(entity.getClass())
				.slash(relProvider.getCollectionResourceRelFor(entity.getClass())).slash(entity.getSourcedId())
				.withRel(relProvider.getItemResourceRelFor(entity.getClass()));
		return link;
	}
}