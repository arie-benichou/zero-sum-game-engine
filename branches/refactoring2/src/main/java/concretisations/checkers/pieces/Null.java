package concretisations.checkers.pieces;

import java.util.Set;

import abstractions.cell.API.CellInterface;
import abstractions.cell.mutation.MutationInterface;
import abstractions.piece.AbstractNullPiece;
import abstractions.side.API.SideInterface;

import com.google.common.collect.ImmutableSet;


public class Null extends AbstractNullPiece {
    
    private final static Set<MutationInterface> AVAILABLE_MUTATIONS = ImmutableSet.of();

    @Override
    public Set<MutationInterface> computeAvailableMutations(CellInterface cell, SideInterface side) {
        return AVAILABLE_MUTATIONS;
    }
    
    public static void main(String[] args) {
        System.out.println(new Null());
    }
    
}
