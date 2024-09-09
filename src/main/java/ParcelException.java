public class ParcelException extends Exception {

    private ParcelErrorCode errorCode;

    public ParcelException(ParcelErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ParcelErrorCode getErrorCode() {
        return errorCode;
    }
}
