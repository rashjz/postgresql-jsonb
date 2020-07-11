package rashjz.info.app.postgresql.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Json {

    public static ObjectMapper configureSingleObjectMapper(ObjectMapper mapper) {
        // don't write null values for maps or objects, just omit them
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // turn off magical coercion
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, false);
        // don't allow nulls to be set on primitives
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);

        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        // configure JDK 8
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());

        return mapper;
    }

    public static ObjectMapper configureObjectMapper(ObjectMapper mapper) {
        AnnotationIntrospector jaxb = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
        AnnotationIntrospector jackson = new JacksonAnnotationIntrospector();
        AnnotationIntrospector both = AnnotationIntrospector.pair(jackson, jaxb);

        // make serializer and deserializer use JAXB annotations
        mapper.setAnnotationIntrospector(both);

        configureSingleObjectMapper(mapper);

        return mapper;
    }

    public static ObjectNode serialize(Object o) {
        ObjectMapper mapper = configureObjectMapper(new ObjectMapper());
        mapper.configure(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME, true);

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return mapper.valueToTree(o);
    }

    public static String toString(Object object) {
        try {
            return Singleton.MAPPER.writeValueAsString(object);
        } catch (Exception ex) {
            return null;
        }
    }

    public static ObjectNode newObject() {
        return Singleton.MAPPER.createObjectNode();
    }

    public static ArrayNode newArray() {
        return Singleton.MAPPER.createArrayNode();
    }

    private static class Singleton {
        private static final ObjectMapper MAPPER = configureSingleObjectMapper(new ObjectMapper());
        private static final ObjectMapper MODEL_CONVERTER_MAPPER = configureSingleObjectMapper(new ObjectMapper())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static ObjectMapper getMapper() {
        return Singleton.MAPPER;
    }

    public static ObjectMapper getModelConverterMapper() {
        return Singleton.MODEL_CONVERTER_MAPPER;
    }

    public static JavaType javaType(Class<?> clazz) {
        return getMapper().getTypeFactory().constructType(clazz);
    }

    public static JavaType javaType(Function<TypeFactory, JavaType> function) {
        return function.apply(getMapper().getTypeFactory());
    }

}
