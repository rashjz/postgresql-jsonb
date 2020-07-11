package rashjz.info.app.postgresql.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperationResponse {

    private List<OperationField> invalid;
    private List<OperationField> successful;
    private OperationStatus status;

    public OperationResponse(OperationRequest request) {
        this(request.getInvalid(), request.getSuccessful());
    }

    public OperationResponse(List<OperationField> invalid, List<OperationField> successful) {
        this.invalid = invalid;
        this.successful = successful;

        if (CollectionUtils.isEmpty(successful)) {
            status = OperationStatus.FAILURE;
        } else if (CollectionUtils.isEmpty(invalid)) {
            status = OperationStatus.SUCCESS;
        } else {
            status = OperationStatus.PARTIAL_SUCCESS;
        }
    }

    public ResponseEntity<OperationResponse> get() {
        if (OperationStatus.FAILURE.equals(status)) {
            return new ResponseEntity<>(this, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(this, HttpStatus.OK);
    }
}
