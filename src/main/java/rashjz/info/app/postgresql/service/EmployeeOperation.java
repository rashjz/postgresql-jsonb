package rashjz.info.app.postgresql.service;

import rashjz.info.app.postgresql.model.dto.EmployeeDto;
import rashjz.info.app.postgresql.model.dto.OperationResponse;

import java.util.List;

public interface EmployeeOperation {

    void create(EmployeeDto spsOnSettings);

    void update(EmployeeDto settingsDto);

    List<EmployeeDto> findAll();

    OperationResponse patch(EmployeeDto settingsDto);
}
