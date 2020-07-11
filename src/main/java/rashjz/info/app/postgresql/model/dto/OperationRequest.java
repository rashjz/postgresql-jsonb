package rashjz.info.app.postgresql.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import rashjz.info.app.postgresql.util.Json;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperationRequest {

    private final Set<OperationField> properties;

    public OperationRequest(Object entity) {
        this.properties = Json.getMapper().<Map<String, Object>>convertValue(entity, new TypeReference<Object>() {})
                .entrySet().stream()
                .map(key -> new OperationField().setField(key.getKey()).setValue(key.getValue()))
                .collect(Collectors.toSet());
    }

    public void addError(String field, String error) {
        properties.stream()
                .filter(partialField -> StringUtils.equals(partialField.getField(), field))
                .findFirst()
                .ifPresent(partialField -> partialField.setError(error));
    }

    @JsonIgnore
    public List<OperationField> getInvalid() {
        return properties.stream()
                .filter(field -> Objects.nonNull(field.getError()))
                .collect(Collectors.toList());
    }

    @JsonIgnore
    public List<OperationField> getSuccessful() {
        return properties.stream()
                .filter(field -> Objects.isNull(field.getError()))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "PartialRequest{" +
                "invalid=" + getInvalid() +
                ", successful=" + getSuccessful() +
                '}';
    }
}
