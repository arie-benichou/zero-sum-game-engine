
package abstractions.piece;

import java.util.Set;

import abstractions.cell.CellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;
import abstractions.side.Sides;

import com.google.common.collect.ImmutableSet;

// TODO ? typer la pièce nulle dynamiquement
public abstract class NullPiece extends PotentialPiece {
    
    public NullPiece() {
        super(Sides.NULL_SIDE);
    }

    @Override
    public final boolean isNull() {
        return true;
    }

    @Override
    public final String toString() {
        return " ";
    }

    public abstract Set<? extends MutationInterface> computeAvailableMutations(CellInterface cell, SideInterface side);

}