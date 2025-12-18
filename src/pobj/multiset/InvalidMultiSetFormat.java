package pobj.multiset;

public class InvalidMultiSetFormat extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidMultiSetFormat(String message) {
        super(message);
    }

    public InvalidMultiSetFormat(String message, Throwable cause) {
        super(message, cause);
    }
}
