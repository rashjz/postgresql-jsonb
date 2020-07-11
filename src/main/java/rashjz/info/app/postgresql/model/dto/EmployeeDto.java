package rashjz.info.app.postgresql.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy.class)
public class EmployeeDto {

    private String key;

    @NotBlank(message = "expected name")
    private String name;

    @NotBlank(message = "expected surname")
    private String surname;

    private long salary;

}
