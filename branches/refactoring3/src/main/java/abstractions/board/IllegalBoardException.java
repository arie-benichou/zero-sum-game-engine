
package abstractions.board;

/**
 * Class for illegal board exceptions.
 */
public class IllegalBoardException extends RuntimeException {

    private static final String MESSAGE = "Board(numberOfRows=%d, numberOfColumns=%d) is not a legal Board.";

    private static final long serialVersionUID = 1L;

    private int numberOfRows;
    private int numberOfColumns;

    public IllegalBoardException(final int numberOfRows, final int numberOfColumns) {
        super();
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE, this.numberOfRows, this.numberOfColumns);
    }

}