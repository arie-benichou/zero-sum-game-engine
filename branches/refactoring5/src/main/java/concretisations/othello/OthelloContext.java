
package concretisations.othello;

import java.util.Map;
import java.util.Stack;

import abstractions.game.Game;
import abstractions.game.GameInterface;
import abstractions.gameplay.GamePlay;
import abstractions.gameplay.GamePlayInterface;
import abstractions.immutable.context.ContextInterface;
import abstractions.immutable.context.adversity.Adversity;
import abstractions.immutable.context.board.cell.piece.side.Side;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.old.cell.CellManagerInterface;
import abstractions.old.cell.ManagedCellInterface;
import abstractions.old.context.Context;
import abstractions.old.mutation.MutationInterface;
import concretisations.othello.mutations.OthelloMutationInterface;

public class OthelloContext extends Context {

    private final int numberOfCells;

    private int numberOfPawnsForFirstSidePlayer = 0;
    private int numberOfPawnsForSecondSidePlayer = 0;

    // TODO c'est l'objet gamePlay qui doit contenir l'historique des coups
    public OthelloContext(final GamePlayInterface gamePlay) {
        super(gamePlay);
        System.out.println("###############FUCK###############");
        int numberOfCells = 0;
        for (final ManagedCellInterface cell : this.getCellManager()) {
            numberOfCells++;
            if (cell.getPiece().side().equals(Side.FIRST))
                ++this.numberOfPawnsForFirstSidePlayer;
            else if (cell.getPiece().side().equals(Side.SECOND))
                ++this.numberOfPawnsForSecondSidePlayer;
        }
        this.numberOfCells = numberOfCells - 1; // moins la cellule nulle
    }

    public OthelloContext(final GamePlayInterface gamePlay, final Map<SideInterface, Stack<MutationInterface>> moves, final SideInterface side
    /*,
    final int numberOfCells,
    final int numberOfPawnsForFirstSidePlayer, final int numberOfPawnsForSecondSidePlayer
    */
    ) {
        super(gamePlay, moves, side);
        int numberOfCells = 0;
        for (final ManagedCellInterface cell : this.getCellManager()) {
            numberOfCells++;
            if (cell.getPiece().side().equals(Side.FIRST))
                ++this.numberOfPawnsForFirstSidePlayer;
            else if (cell.getPiece().side().equals(Side.SECOND))
                ++this.numberOfPawnsForSecondSidePlayer;
        }
        this.numberOfCells = numberOfCells - 1; // moins la cellule nulle
        /*
        // TODO d'où l'interet d'avoir gameplay et contexte...        
        this.numberOfCells = numberOfCells;
        this.numberOfPawnsForFirstSidePlayer = numberOfPawnsForFirstSidePlayer;
        this.numberOfPawnsForSecondSidePlayer = numberOfPawnsForSecondSidePlayer;
        */
    }

    @Override
    public ContextInterface duplicate() {

        final CellManagerInterface cellManager = this.getCellManager().duplicate();
        final GameInterface game = new Game(cellManager, new OthelloReferee());
        final Adversity adversity = new Adversity(this.getAdversity().getPlayer(Side.FIRST), this.getAdversity().getPlayer(Side.SECOND));
        final GamePlay gamePlay = new GamePlay(game, adversity);

        return new OthelloContext(gamePlay, this.moves, this.getSide());

        //return new OthelloContext(new GamePlay(new Game(this.getCellManager().duplicate(), this.getReferee()), this.getAdversity()), this.moves, this.getCurrentSide()

        /*,
        this.getNumberOfCells(), this.numberOfPawnsForFirstSidePlayer, this.numberOfPawnsForSecondSidePlayer);
        */
    }

    public synchronized final int getNumberOfCells() {
        return this.numberOfCells;
    }

    public synchronized final int getNumberOfEmptyCells() {
        return this.getNumberOfCells() - (this.getNumberOfPawns(Side.FIRST) + this.getNumberOfPawns(Side.SECOND));
    }

    public synchronized final int getNumberOfPawns(final SideInterface side) {
        return side.isFirst() ? this.numberOfPawnsForFirstSidePlayer : this.numberOfPawnsForSecondSidePlayer;
    }

    @Override
    public synchronized final MutationInterface onApplyMove(final MutationInterface move) {
        if (!move.isNull()) {
            final OthelloMutationInterface othelloMove = (OthelloMutationInterface) move;
            this.numberOfPawnsForFirstSidePlayer += othelloMove.getFirstSideDelta();
            this.numberOfPawnsForSecondSidePlayer += othelloMove.getSecondSideDelta();
        }
        return move;
    }

    @Override
    public synchronized final MutationInterface onUnapplyMove(final MutationInterface move) {
        if (!move.isNull()) {
            final OthelloMutationInterface othelloMove = (OthelloMutationInterface) move;
            this.numberOfPawnsForFirstSidePlayer -= othelloMove.getFirstSideDelta();
            this.numberOfPawnsForSecondSidePlayer -= othelloMove.getSecondSideDelta();
        }
        return move;
    }

    @Override
    public String toString() {
        return super.toString()
                + this.getAdversity().getPlayer(Side.FIRST).name() + " : " + this.getNumberOfPawns(Side.FIRST)
                + " | "
                + this.getAdversity().getPlayer(Side.SECOND).name() + " : " + this.getNumberOfPawns(Side.SECOND)
                + "\n";
    }

}