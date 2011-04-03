
package abstractions.piece;

import static abstractions.side.API.NULL_SIDE;

import java.util.Set;

import abstractions.cell.API.CellInterface;
import abstractions.cell.mutation.MutationInterface;
import abstractions.side.API.SideInterface;

import com.google.common.collect.ImmutableSet;

// TODO en faire une classe abstraite
// Chaque jeu implémenté doit définir le comportement de la pièce nulle.
final class NullPiece extends PotentialPiece {

    public NullPiece() {
        super(NULL_SIDE);
    }

    @Override
    public final boolean isNull() {
        return true;
    }

    @Override
    public final String toString() {
        return " ";
    }

    
    
    // spécialisation pour checkers    
    private final static Set<MutationInterface> AVAILABLE_MUTATIONS = ImmutableSet.of();
    
    // TODO ! en faire une méthode abstraite
    public Set<MutationInterface> computeAvailableMutations(final CellInterface cell, SideInterface side) {
        
        // spécialisation pour checkers
        return AVAILABLE_MUTATIONS;
        
    }
    

}