
package concretisations.othello.pieces;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import abstractions.cell.CellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.piece.AbstractPiece;
import abstractions.position.RelativePositionInterface;
import abstractions.position.RelativePositions;
import abstractions.side.SideInterface;

public abstract class OthelloPiece extends AbstractPiece implements OthelloPieceInterface {
    
    // TODO ? avoir une méthode dans Cell qui pemet de récupérer toutes cases les voisines    
    public static List<RelativePositionInterface> NEIGHBOURS_POSITIONS = ImmutableList.of
    (
            RelativePositions.TOP,
            RelativePositions.RIGHT,
            RelativePositions.BOTTOM,
            RelativePositions.LEFT,
            RelativePositions.TOP_RIGHT,
            RelativePositions.BOTTOM_RIGHT,            
            RelativePositions.TOP_LEFT,
            RelativePositions.BOTTOM_LEFT
    );                
    
    protected final static Set<? extends MutationInterface> EMPTY_MUTATION_SET = ImmutableSet.of();    

    public OthelloPiece(SideInterface side) {
        super(side);
    }

    public boolean willItBeConnected(CellInterface cell, SideInterface side, RelativePositionInterface relativePosition) {
        boolean willBeConnected = false;
        if (side.equals(cell.getPiece().getSide())) {
            willBeConnected = true;
        }
        else if (side.getNextSide().equals(cell.getPiece().getSide())) {
            CellInterface nextCell = cell.getRelative(relativePosition);
            willBeConnected = ((OthelloPiece) nextCell.getPiece()).willItBeConnected(nextCell, side, relativePosition);
        }
        return willBeConnected;
    }

    protected boolean isMutable(CellInterface cell, SideInterface side) {
        boolean willBeConnected = false;
        for (int index = 0, maxIndex = NEIGHBOURS_POSITIONS.size(); index < maxIndex && !willBeConnected; ++index) {
            RelativePositionInterface relativePosition = NEIGHBOURS_POSITIONS.get(index);
            CellInterface nextCell = cell.getRelative(relativePosition);
            if(nextCell.isNull()) {
                continue; //TODO revoir la partie concernant la pièce nulle
            }
            if(side.getNextSide().equals(nextCell.getPiece().getSide())) {
                CellInterface nextNextCell = nextCell.getRelative(relativePosition);
                OthelloPiece nextNextPiece = (OthelloPiece) nextNextCell.getPiece();
                willBeConnected = nextNextPiece.willItBeConnected(nextNextCell, side, relativePosition);
            }
        }
        return willBeConnected;
    }

    public abstract Set<? extends MutationInterface> computeAvailableMutations(CellInterface cell, SideInterface side);

}