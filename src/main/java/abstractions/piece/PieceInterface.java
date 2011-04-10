
package abstractions.piece;

import java.util.Set;

import abstractions.cell.ManagedCellInterface;
import abstractions.mutation.MutationTypeInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableSet;

/**
 * This is the interface for a piece.
 */
public interface PieceInterface {

    final static Set<? extends MutationTypeInterface> NULL_POTENTIAL_MUTATION_TYPES_SET = ImmutableSet.of();

    /**
     * Returns the side related to this piece.
     * 
     * @return the side related to this piece
     */
    SideInterface getSide();

    /**
     * Returns the type related to this piece.
     * 
     * @return the type related to this piece
     */
    PieceTypeInterface getType();

    Set<? extends MutationTypeInterface> computePotentialMutationTypes(final ManagedCellInterface cell, SideInterface side);

    // TODO add method isNull()

}