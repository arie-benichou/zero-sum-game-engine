/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package fr.designpattern.zerosumgames.framework.service.gameplay.game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.GameBoardInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.positions.PositionInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

/**
 * This class provides a skeletal implementation of the Game interface, to
 * minimize the effort required to implement this interface.
 */
public abstract class AbstractGame implements GameInterface {

    // ---------------------------------------------------------------------
    // Object Internals
    // ---------------------------------------------------------------------
    /*
     * private final transient PiecesInterface pieceFactory;
     * 
     * protected final PiecesInterface getPieceFactory() { return
     * this.pieceFactory; }
     */
    // ---------------------------------------------------------------------
    private final transient GameBoardInterface board;

    protected final GameBoardInterface getBoard() {
        return this.board;
    }

    // ---------------------------------------------------------------------
    public AbstractGame(/* final PiecesInterface pieceFactory, */
    final GameBoardInterface board) {
        //this.pieceFactory = pieceFactory;
        //this.board = board.clone();
        this.board = board;
    }

    // ---------------------------------------------------------------------
    /*
     * public AbstractGame(final GameInterface gameToClone) {
     * this(gameToClone.getBoard()); }
     */
    // ---------------------------------------------------------------------
    @Override
    public final GameInterface clone() {

        Constructor<? extends AbstractGame> constructor = null;

        GameInterface instance = null;

        try {
            constructor = this.getClass().getDeclaredConstructor(
                    GameBoardInterface.class);
        }
        catch (final SecurityException e) {
            e.printStackTrace();
        }
        catch (final NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            instance = constructor.newInstance(this.getBoard().clone());
        }
        catch (final IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (final InstantiationException e) {
            e.printStackTrace();
        }
        catch (final IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (final InvocationTargetException e) {
            e.printStackTrace();
        }

        return instance;

    }

    // ---------------------------------------------------------------------
    @Override
    public final String toString() {
        return this.getClass().getSimpleName() + this.getBoard().toString();
    }

    // ---------------------------------------------------------------------
    // Façades fournies
    // ---------------------------------------------------------------------

    /*
     * public final PieceInterface piece(final OpponentsEnumeration player,
     * final PieceTypeInterface pieceType) { return
     * this.getPieceFactory().getPiece(player, pieceType); }
     */

    public final BoardCellInterface cell(final BoardPositionInterface position) {
        return this.getBoard().cell(position);
    }

    public final BoardCellInterface cell(final int clientRowIndex,
            final int clientColumnIndex) {
        return this.getBoard().cell(clientRowIndex, clientColumnIndex);
    }

    // ---------------------------------------------------------------------
    // Implémentations finales
    // ---------------------------------------------------------------------

    public final OpponentsEnumeration computeNextSideToPlay(
            final LegalMoveInterface playedMove, final boolean isMoveDone) {

        OpponentsEnumeration nexSideToPlay;

        if (!isMoveDone) {
            nexSideToPlay = playedMove.getSide();
        }
        else
            if (this.isGameOverFromVictory(playedMove)) {
                nexSideToPlay = playedMove.getSide().getOpponent()
                        .getNegation();
            }
            else
                if (this.isGameOverFromDraw(playedMove)) {
                    nexSideToPlay = OpponentsEnumeration.NO_ONE;
                }
                else {
                    nexSideToPlay = OpponentsEnumeration.opponent(playedMove
                            .getSide());
                }

        return nexSideToPlay;

    }

    public final OpponentsEnumeration play(final LegalMoveInterface moveToPlay) {
        return this.computeNextSideToPlay(moveToPlay, this.doMove(moveToPlay));
    }

    // ---------------------------------------------------------------------
    // Méthodes à implémenter
    // ---------------------------------------------------------------------

    public abstract boolean hasNullMove();

    public abstract List<LegalMoveInterface> getLegalMoves(
            OpponentsEnumeration side);

    public abstract boolean doMove(LegalMoveInterface moveToPlay);

    public abstract boolean undoMove(LegalMoveInterface playedMove);

    public abstract boolean isGameOverFromVictory(LegalMoveInterface playedMove);

    public abstract boolean isGameOverFromDraw(LegalMoveInterface playedMove);

    public abstract double computeStaticEvaluation(LegalMoveInterface playedMove);

    //public abstract PieceInterface piece(final OpponentsEnumeration player,
    //        final PieceTypeInterface pieceType);

}