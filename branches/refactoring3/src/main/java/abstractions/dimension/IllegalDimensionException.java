
package abstractions.dimension;

/**
 * Class for illegal dimension exceptions.
 */
public final class IllegalDimensionException extends RuntimeException {

    private static final String MESSAGE = "Dimension(numberOfRows=%d, numberOfColumns=%d) is not a legal dimension.";

    private static final long serialVersionUID = 1L;

    private final int numberOfRows;
    private final int numberOfColumns;

    public IllegalDimensionException(final int numberOfRows, final int numberOfColumns) {
        super();
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    @Override
    public String getMessage() {
        return String.format(IllegalDimensionException.MESSAGE, this.numberOfRows, this.numberOfColumns);
    }

}