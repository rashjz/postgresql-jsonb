package rashjz.info.app.postgresql.service.impl;

import rashjz.info.app.postgresql.convertor.ModelConverter;
import rashjz.info.app.postgresql.exception.EmployeeDoesNotExist;
import rashjz.info.app.postgresql.exception.EmployeeInvalid;
import rashjz.info.app.postgresql.model.dto.OperationRequest;
import rashjz.info.app.postgresql.model.dto.OperationResponse;
import rashjz.info.app.postgresql.model.dto.EmployeeDto;
import rashjz.info.app.postgresql.model.entities.Employee;
import rashjz.info.app.postgresql.repository.EmployeeRepository;
import rashjz.info.app.postgresql.service.EmployeeOperation;
import rashjz.info.app.postgresql.validator.EmployeeValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
class EmployeeOperationImpl implements EmployeeOperation {
    private final EmployeeRepository settingsRepository;

    public void create(EmployeeDto dto) {
        if (!EmployeeValidator.isValidPost(dto)) {
            log.debug("rejected to save invalid dto '{}'", dto);
            throw new EmployeeInvalid();
        }
        var localSettings = ModelConverter.toEntityRow(Employee.class, dto);
        localSettings.setKey(UUID.randomUUID().toString());

        settingsRepository.save(localSettings);
    }

    @Override
    public void update(EmployeeDto dto) {
        boolean exists = settingsRepository.existsById(dto.getKey());
        if (!exists) {
            throw new EmployeeDoesNotExist(dto.getKey());
        }
        if (!EmployeeValidator.isValidPut(dto)) {
            throw new EmployeeInvalid();
        }
        var settings = ModelConverter.toEntityRow(Employee.class, dto);

        settingsRepository.save(settings);
    }

    public List<EmployeeDto> findAll() {
        return settingsRepository.findAll().stream()
                .map(employee ->
                        ModelConverter.toBusinessEntity(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OperationResponse patch(EmployeeDto dto) {
        var request = new OperationRequest(dto);
        if (EmployeeValidator.isValidPatch(dto)) {
            var settings = ModelConverter.toDao(dto, Employee.class);
            settingsRepository.save(settings);
        }
        return new OperationResponse(request);
    }
}

