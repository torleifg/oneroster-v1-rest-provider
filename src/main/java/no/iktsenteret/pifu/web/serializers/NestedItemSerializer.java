package no.iktsenteret.pifu.web.serializers;

import java.io.IOException;

import org.springframework.hateoas.Link;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import no.iktsenteret.pifu.domain.Base;
import no.iktsenteret.pifu.web.response.ApiResponse;
import no.iktsenteret.pifu.web.response.GUIDRef;

public class NestedItemSerializer<T extends Base> extends JsonSerializer<T> {

	private final ApiResponse apiResponse;

	public NestedItemSerializer(ApiResponse apiResponse) {
		this.apiResponse = apiResponse;
	}

	@Override
	public void serialize(T entity, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		Link link = apiResponse.linkFor(entity);
		generator.writeObject(new GUIDRef(entity, link));
	}
}
