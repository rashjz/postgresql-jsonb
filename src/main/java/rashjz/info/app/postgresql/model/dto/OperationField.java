package rashjz.info.app.postgresql.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode
public class OperationField {

    private String field;
    private Object value;
    private String error;

    @Override
    public String toString() {
        return "PartialField{" +
                "field='" + getField() + '\'' +
                ", value=" + getValue() +
                ", error='" + getError() + '\'' +
                '}';
    }
}
