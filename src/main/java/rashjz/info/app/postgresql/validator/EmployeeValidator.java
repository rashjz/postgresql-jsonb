package rashjz.info.app.postgresql.validator;

import rashjz.info.app.postgresql.model.dto.EmployeeDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EmployeeValidator {

    public static boolean isValidPut(EmployeeDto dto) {
        return StringUtils.isNotBlank(dto.getKey()) &&
                StringUtils.isNotBlank(dto.getName())
                && StringUtils.isNotBlank(dto.getSurname());
    }

    public static boolean isValidPost(EmployeeDto dto) {
        return StringUtils.isBlank(dto.getKey()) &&
                StringUtils.isNotBlank(dto.getName())
                && StringUtils.isNotBlank(dto.getSurname());
    }

    public static boolean isValidPatch(EmployeeDto dto) {
        return StringUtils.isNotBlank(dto.getKey());
    }
}
