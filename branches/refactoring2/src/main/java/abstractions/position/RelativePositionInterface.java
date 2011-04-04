
package abstractions.position;

public interface RelativePositionInterface {

    /**
     * Returns the column index of this position.
     * 
     * @return the column index of this position
     */
    int getColumnDelta();

    /**
     * Returns the row index of this position.
     * 
     * @return the row index of this position
     */
    int getRowDelta();
    
    
    int computeRow(int row);
    int computeColumn(int column);

}