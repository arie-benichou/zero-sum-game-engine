
package abstractions.direction;

public class IllegalDirectionException extends RuntimeException {

    private static final String MESSAGE = "Direction(rowDelta=%s, columnDelta=%s) is not a legal direction.";

    private static final long serialVersionUID = 1L;

    private final int columnDelta;
    private final int rowDelta;

    public IllegalDirectionException(final int rowDelta, final int columnDelta) {
        super();
        this.rowDelta = rowDelta;
        this.columnDelta = columnDelta;
    }

    @Override
    public String getMessage() {
        return String.format(IllegalDirectionException.MESSAGE, this.rowDelta, this.columnDelta);
    }

}
