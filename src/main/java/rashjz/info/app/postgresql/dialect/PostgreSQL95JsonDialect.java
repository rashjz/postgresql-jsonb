package rashjz.info.app.postgresql.dialect;

import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import org.hibernate.dialect.PostgreSQL95Dialect;

import java.sql.Types;

/**
 * Introduce new dialect for handling the json and jsonb types of a column's value
 */
public class PostgreSQL95JsonDialect extends PostgreSQL95Dialect {

    public PostgreSQL95JsonDialect() {
        super();
        this.registerHibernateType(
                Types.OTHER, JsonNodeBinaryType.class.getName()
        );
    }
}
