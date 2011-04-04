
package abstractions.side;

/**
 * This is the interface for a side.
 */
public interface SideInterface {

    /**
     * Returns true if this is the null side, false otherwise.
     * 
     * @return true if this is the null side, false otherwise
     */
    boolean isNull();

    /**
     * Return true if this is the first side, false otherwise.
     * 
     * @return true if this is the first side, false otherwise
     */
    boolean isFirstSide();

    /**
     * Return true if this is the second side, false otherwise.
     * 
     * @return true if this is the second side, false otherwise
     */
    boolean isSecondSide();

    /**
     * Return true if this is the first or second side, false otherwise.
     * 
     * @return true if this is the first or second side, false otherwise
     */
    boolean isOneSide();

    /**
     * Returns the side after this one.
     * 
     * @return the side after this one
     */
    abstract SideInterface getNextSide();

    /**
     * Returns the negation of this side.
     * 
     * @return the negation of this side
     */
    abstract SideInterface getNegation();

}