package rashjz.info.app.postgresql.model.entities;

import com.fasterxml.jackson.databind.JsonNode;

public interface KVTable {

    String getKey();

    JsonNode getValue();
}
