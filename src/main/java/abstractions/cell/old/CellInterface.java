
package abstractions.cell.old;

import java.util.Set;

import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;

/**
 * This is the interface for a cell.
 */
public interface CellInterface extends Comparable<CellInterface> {

    Set<? extends MutationInterface> fetchAvailableMutations(SideInterface side);

    void willGenerateMutations(boolean willItGenerateMutations);

    /**
     * Returns true if this cell has at least one legal mutation for a side to
     * play.
     * 
     * @return true if this cell has at least one legal mutation for a side to
     *         play
     */
    boolean willGenerateMutations();

}