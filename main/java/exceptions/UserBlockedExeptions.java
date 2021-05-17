package exceptions;

public class UserBlockedExeptions extends Exception {
    private String message;

    public UserBlockedExeptions(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
