
package concretisations.checkers.pieces;

import java.util.Set;

import abstractions.cell.old.CellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.position.relative.RelativePositionInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableSet;

public class Null extends CheckerPiece {

    // TODO à mettre dans chaque classe abstraite d'une pièce d'un jeu et à laisser mutable    
    private final static Set<? extends MutationInterface> NULL_MUTATIONS = ImmutableSet.of();
    private final static Set<RelativePositionInterface> NULL_DIRECTIONS = ImmutableSet.of();

    public Null(SideInterface side) {
        super(side, NULL_DIRECTIONS);
    }

    public Set<? extends MutationInterface> computeAvailableMutations(CellInterface cell, SideInterface side) {
        return NULL_MUTATIONS;
    }

}
