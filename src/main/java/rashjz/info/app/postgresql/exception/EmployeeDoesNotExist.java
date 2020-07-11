package rashjz.info.app.postgresql.exception;

public class EmployeeDoesNotExist extends RuntimeException {

    private static final String MESSAGE = "employee does not exist";

    private final String Id;

    public EmployeeDoesNotExist(String Id) {
        super(MESSAGE);
        this.Id = Id;
    }
}
