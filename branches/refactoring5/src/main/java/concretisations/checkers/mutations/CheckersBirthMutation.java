
package concretisations.checkers.mutations;

import abstractions.direction.DirectionManager.NamedDirection;
import abstractions.immutable.context.gameplay.game.board.cell.piece.OldPieceTypeInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;
import abstractions.old.cell.ManagedCellInterface;
import abstractions.old.mutation.AtomicMutationTypes;
import abstractions.old.mutation.Birth;
import concretisations.checkers.pieces.CheckersPieceSet;

// TODO ! à virer
public class CheckersBirthMutation extends Birth {

    public CheckersBirthMutation(final ManagedCellInterface cell, final OldPieceTypeInterface pieceType, final SideInterface side) {
        super(cell, AtomicMutationTypes.BIRTH, pieceType, side);
    }

    @Override
    public void process() {

        if (this.getPieceType().equals(CheckersPieceSet.MAN)) {

            //System.out.println(this.getCell().getNeighbour(NamedDirection.TOP));
            //System.out.println(this.getCell().getNeighbour(NamedDirection.BOTTOM));

            // TODO à revoir si la règle "manger en arrière" est autorisée
            if (this.getPosition().getNeighbour(NamedDirection.TOP).isNull() || this.getPosition().getNeighbour(NamedDirection.BOTTOM).isNull()) {
                System.out.println("## POMOTION PION -> DAME ##");
                this.getPosition().setPiece(this.getSide(), CheckersPieceSet.KING);
            }
            else
                this.getPosition().setPiece(this.getSide(), this.getPieceType()); // TODO à simplifier avec un &&

        }
        else
            this.getPosition().setPiece(this.getSide(), this.getPieceType());
    }

}
