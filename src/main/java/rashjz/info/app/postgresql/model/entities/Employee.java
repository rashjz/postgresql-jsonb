package rashjz.info.app.postgresql.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee extends KeyValue {

    @Override
    public String toString() {
        return "employee" + super.toString();
    }
}
