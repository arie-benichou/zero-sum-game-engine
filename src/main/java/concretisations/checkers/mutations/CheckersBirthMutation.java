
package concretisations.checkers.mutations;

import abstractions.cell.ManagedCellInterface;
import abstractions.direction.DirectionManager.NamedDirection;
import abstractions.mutation.AtomicMutationTypes;
import abstractions.mutation.Birth;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;
import concretisations.checkers.pieces.CheckersPieceSet;

public class CheckersBirthMutation extends Birth {

    public CheckersBirthMutation(ManagedCellInterface cell, PieceTypeInterface pieceType, SideInterface side) {
        super(cell, AtomicMutationTypes.BIRTH, pieceType, side);
    }

    @Override
    public void process() {

        if (this.getPieceType().equals(CheckersPieceSet.MAN)) {

            //System.out.println(this.getCell().getNeighbour(NamedDirection.TOP));
            //System.out.println(this.getCell().getNeighbour(NamedDirection.BOTTOM));

            // TODO à revoir si la règle "manger en arrière" est autorisée
            if (this.getCell().getNeighbour(NamedDirection.TOP).isNull() || this.getCell().getNeighbour(NamedDirection.BOTTOM).isNull()) {
                System.out.println("## POMOTION PION -> DAME ##");
                this.getCell().setPiece(this.getSide(), CheckersPieceSet.KING);
            }
            else
                this.getCell().setPiece(this.getSide(), this.getPieceType()); // TODO à simplifier avec un &&

        }
        else
            this.getCell().setPiece(this.getSide(), this.getPieceType());
    }

}
