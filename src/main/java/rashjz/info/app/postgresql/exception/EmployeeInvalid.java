package rashjz.info.app.postgresql.exception;


public class EmployeeInvalid extends RuntimeException {

    private static final String MESSAGE = "invalid employee";

    public EmployeeInvalid() {
        super(MESSAGE);
    }

}