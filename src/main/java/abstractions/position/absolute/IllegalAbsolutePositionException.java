
package abstractions.position.absolute;

/**
 * Class for illegal positions.
 */
public final class IllegalAbsolutePositionException extends RuntimeException {

    private static final String MESSAGE = "Position(row=%d, column=%d) is not a legal position.";

    private static final long serialVersionUID = 1L;

    private int rowIndex;
    private int columnIndex;

    public IllegalAbsolutePositionException(final int rowIndex, final int columnIndex) {
        super();
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE, this.rowIndex, this.columnIndex);
    }

}