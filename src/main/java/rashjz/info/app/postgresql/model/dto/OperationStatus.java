package rashjz.info.app.postgresql.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public enum OperationStatus {

    @JsonProperty("success") SUCCESS,
    @JsonProperty("failure") FAILURE,
    @JsonProperty("partial-success") PARTIAL_SUCCESS;

    public HttpStatus httpStatus() {
        switch (this) {
            case FAILURE:
                return HttpStatus.BAD_REQUEST;
            case SUCCESS:
            case PARTIAL_SUCCESS:
            default:
                return HttpStatus.OK;
        }
    }
}
