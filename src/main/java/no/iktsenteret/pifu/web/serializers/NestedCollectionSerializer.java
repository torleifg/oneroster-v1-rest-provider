package no.iktsenteret.pifu.web.serializers;

import java.io.IOException;
import java.util.Set;

import org.springframework.hateoas.Link;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import no.iktsenteret.pifu.domain.Base;
import no.iktsenteret.pifu.web.response.ApiResponse;
import no.iktsenteret.pifu.web.response.GUIDRef;

public class NestedCollectionSerializer<T extends Base> extends JsonSerializer<Set<T>> {

	private final ApiResponse apiResponse;

	public NestedCollectionSerializer(ApiResponse apiResponse) {
		this.apiResponse = apiResponse;
	}

	@Override
	public void serialize(Set<T> entities, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		generator.writeStartArray();
		for (T entity : entities) {
			Link link = apiResponse.linkFor(entity);
			generator.writeObject(new GUIDRef(entity, link));
		}
		generator.writeEndArray();
	}
}
