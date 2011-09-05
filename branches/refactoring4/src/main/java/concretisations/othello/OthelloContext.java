
package concretisations.othello;

import abstractions.cell.ManagedCellInterface;
import abstractions.context.Context;
import abstractions.gameplay.GamePlayInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;
import abstractions.side.Sides;
import concretisations.othello.mutations.OthelloMutationInterface;

public class OthelloContext extends Context {

    private final Double numberOfCells;

    private Integer numberOfPawnsForFirstSidePlayer = 0;
    private Integer numberOfPawnsForSecondSidePlayer = 0;

    public OthelloContext(final GamePlayInterface gamePlay) {
        super(gamePlay);
        int numberOfCells = 0;
        for (final ManagedCellInterface cell : this.getCellManager()) {
            numberOfCells++;
            if (cell.getPiece().getSide().equals(Sides.FIRST))
                ++this.numberOfPawnsForFirstSidePlayer;
            else if (cell.getPiece().getSide().equals(Sides.SECOND))
                ++this.numberOfPawnsForSecondSidePlayer;
        }
        this.numberOfCells = numberOfCells - 1.0; // moins la cellule nulle
    }

    public final double getNumberOfCells() {
        return this.numberOfCells;
    }

    public final double getNumberOfEmptyCells() {
        return this.getNumberOfCells() - (this.getNumberOfPawns(Sides.FIRST) + this.getNumberOfPawns(Sides.SECOND));
    }

    public final Integer getNumberOfPawns(final SideInterface side) {
        return side.isFirstSide() ? this.numberOfPawnsForFirstSidePlayer : this.numberOfPawnsForSecondSidePlayer;
    }

    @Override
    public final void onApplyMove(final MutationInterface move) {
        if (!move.isNull()) {
            final OthelloMutationInterface othelloMove = (OthelloMutationInterface) move;
            this.numberOfPawnsForFirstSidePlayer += othelloMove.getFirstSideDelta();
            this.numberOfPawnsForSecondSidePlayer += othelloMove.getSecondSideDelta();
        }
    }

    @Override
    public final void onUnapplyMove(final MutationInterface move) {
        if (!move.isNull()) {
            final OthelloMutationInterface othelloMove = (OthelloMutationInterface) move;
            this.numberOfPawnsForFirstSidePlayer -= othelloMove.getFirstSideDelta();
            this.numberOfPawnsForSecondSidePlayer -= othelloMove.getSecondSideDelta();
        }
    }

    @Override
    public String toString() {
        return super.toString()
                + this.getAdversity().getPlayer(Sides.FIRST).getName() + " : " + this.getNumberOfPawns(Sides.FIRST)
                + " | "
                + this.getAdversity().getPlayer(Sides.SECOND).getName() + " : " + this.getNumberOfPawns(Sides.SECOND)
                + "\n";
    }
}