package rashjz.info.app.postgresql.model.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class KeyValue implements KVTable {

    @Id
    @EqualsAndHashCode.Include
    private String key;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JsonNode value;

    @Override
    public String toString() {
        return "{key: " + getKey()
                + ", value: " + getValue() + "}";
    }
}

