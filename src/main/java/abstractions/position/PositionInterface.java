
package abstractions.position;


/**
 * This is the interface for a position.
 */
public interface PositionInterface extends Comparable<PositionInterface> {

    /**
     * Returns the column index of this position.
     * 
     * @return the column index of this position
     */
    int getColumn();

    /**
     * Returns the row index of this position.
     * 
     * @return the row index of this position
     */
    int getRow();

    /**
     * Returns true if this position is the null object, false otherwise.
     * 
     * @return true if this position is the null object, false otherwise
     */
    boolean isNull();

}