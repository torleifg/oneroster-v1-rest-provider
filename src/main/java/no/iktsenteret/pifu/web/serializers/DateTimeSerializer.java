package no.iktsenteret.pifu.web.serializers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class DateTimeSerializer {

	public static class LocalDateSerializer extends JsonSerializer<LocalDate> {

		private static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

		@Override
		public void serialize(LocalDate localDate, JsonGenerator generator, SerializerProvider provider)
				throws IOException, JsonProcessingException {
			generator.writeString(formatter.format(localDate));
		}
	}

	public static class ZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

		private static DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME.withZone(ZoneId.of("Z"));

		@Override
		public void serialize(ZonedDateTime zonedDateTime, JsonGenerator generator, SerializerProvider provider)
				throws IOException, JsonProcessingException {
			generator.writeString(formatter.format(zonedDateTime));
		}
	}
}
