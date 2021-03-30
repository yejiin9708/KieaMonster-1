package org.tain.config;

//@Configuration
public class _JacksonConfiguration {

	/*
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
	
	@Bean
	@Primary
	public ObjectMapper serializingObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
		javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
		objectMapper.registerModule(javaTimeModule);
		return objectMapper;
	}
	
	public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
		@Override
		public void serialize(LocalDateTime value, JsonGenerator generator, SerializerProvider serializers) throws IOException {
			generator.writeString(value.format(FORMATTER));
		}
	}
	
	public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
		@Override
		public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
			return LocalDateTime.parse(parser.getValueAsString(), FORMATTER);
		}
	}
	*/
}
