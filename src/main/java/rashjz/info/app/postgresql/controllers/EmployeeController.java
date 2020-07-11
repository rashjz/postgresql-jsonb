package rashjz.info.app.postgresql.controllers;

import rashjz.info.app.postgresql.model.dto.OperationResponse;
import rashjz.info.app.postgresql.model.dto.EmployeeDto;
import rashjz.info.app.postgresql.service.EmployeeOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(
        tags = "employee controller",
        value = "v1",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Slf4j
@RestController
@RequestMapping("/spson/local-settings")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeOperation spsOnSettingsOperation;

    @PostMapping
    @ApiOperation(
            value = "Create new employee",
            notes = "Store the employee on db."
    )
    public void create(@RequestBody EmployeeDto settingsDto) {
        spsOnSettingsOperation.create(settingsDto);
    }

    @PutMapping(value = "/update")
    @ApiOperation(
            value = "Update employee",
            notes = "Store the SSM SPS ON settings."
    )
    public void update(@RequestBody EmployeeDto settingsDto) {
        spsOnSettingsOperation.update(settingsDto);
    }

    @GetMapping
    @ApiOperation(
            value = "Get All employees"
    )
    public List<EmployeeDto> getAll() {
        return spsOnSettingsOperation.findAll();
    }

    @PatchMapping
    @ApiOperation(
            value = "Patch employee"
    )
    public ResponseEntity<OperationResponse> replace(@RequestBody EmployeeDto settingsDto) {
        return spsOnSettingsOperation.patch(settingsDto).get();
    }
}
