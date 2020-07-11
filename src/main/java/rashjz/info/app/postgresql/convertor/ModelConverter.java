package rashjz.info.app.postgresql.convertor;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import rashjz.info.app.postgresql.model.entities.KeyValue;
import rashjz.info.app.postgresql.model.entities.KVTable;
import rashjz.info.app.postgresql.util.Json;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.var;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Converter helps to support information transferring between Data and API layers
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ModelConverter {

    private static final JavaType TABLE_MAP = TypeFactory.defaultInstance()
            .constructMapType(HashMap.class, String.class, JsonNode.class);

    public static <T> T toBusinessEntity(List<? extends KVTable> entity, Class<T> clazz) {
        var result = entity.stream().collect(Collectors.toMap(KVTable::getKey, KVTable::getValue));
        return Json.getModelConverterMapper().convertValue(result, clazz);
    }

    public static <T> T toBusinessEntity(KVTable entity, Class<T> clazz) {
        return Json.getModelConverterMapper().convertValue(entity.getValue(), clazz);
    }

    public static <T> T toGenericBusinessEntity(KVTable entity, Class<T> clazz) {
        return Json.getModelConverterMapper().convertValue(entity, clazz);
    }

    public static <T> T toDao(Object objective, Class<T> original) {
        return Json.getModelConverterMapper().convertValue(objective, original);
    }

    public static Map<String, JsonNode> toMap(Object dto) {
        return Json.getModelConverterMapper().convertValue(dto, TABLE_MAP);
    }

    public static <T extends KeyValue> T toEntityRow(Class<T> clazz, Object dto) {
        try {
            Map map = toMap(dto);
            var entity = clazz.getConstructor().newInstance();
            Optional.ofNullable((TextNode) map.remove("key")).ifPresent(node -> entity.setKey(node.textValue()));

            entity.setValue(Json.getModelConverterMapper().convertValue(map, JsonNode.class));
            return entity;
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

}
