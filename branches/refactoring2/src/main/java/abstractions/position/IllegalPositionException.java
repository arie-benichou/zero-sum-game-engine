
package abstractions.position;

/**
 * Class for illegal positions.
 */
public final class IllegalPositionException extends RuntimeException {

    private static final String MESSAGE = "Position(row=%d, column=%d) is not a legal position.";

    private static final long serialVersionUID = 1L;

    private int rowIndex;
    private int columnIndex;

    public IllegalPositionException(final int rowIndex, final int columnIndex) {
        super();
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE, this.rowIndex, this.columnIndex);
    }

}