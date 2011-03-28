/*
 * Copyright 2011 Arié Bénichou
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

package fr.designpattern.zerosumgames.samples.tictactoe;

import java.util.ArrayList;
import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.AbstractGame;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.GameBoardInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.BoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.BoardPlane;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.BoardDimensions;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.pieces.PieceInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.pieces.Pieces;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.positions.PositionInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMove;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

public class Tictactoe extends AbstractGame {

    // ------------------------------------------------------------
    public static final int CONNECTIONS = 4;
    public static final Class<TictactoePieceTypes> PIECE_TYPES = TictactoePieceTypes.class;
    public static final BoardDimensions BOARD_DIMENSION = new BoardDimensions(1, 6, 1, 6);
    // ------------------------------------------------------------
    private transient int connections;
    protected final Pieces pieceFactory;

    // ------------------------------------------------------------
    public Tictactoe(final GameBoardInterface board) {
        this(board, Tictactoe.CONNECTIONS);
    }

    // ------------------------------------------------------------
    public Tictactoe(final GameBoardInterface board, final int connections) {
        super(board);
        this.connections = connections;
        this.pieceFactory = new Pieces(Tictactoe.PIECE_TYPES);
    }

    // ------------------------------------------------------------
    @Override
    public boolean hasNullMove() {
        return false;
    }

    // ------------------------------------------------------------
    // TODO ? implémentation par défaut dans la classe abstraite
    protected LegalMoveInterface makeMove(final OpponentsEnumeration side,
            final BoardPositionInterface position) {
        // TODO utiliser un cache
        return new LegalMove(side, position);
    }

    // ------------------------------------------------------------
    @Override
    public List<LegalMoveInterface> getLegalMoves(
            final OpponentsEnumeration side) {

        //System.out.println("from getLegalMoves : " + this);

        final List<LegalMoveInterface> legalMoves = new ArrayList<LegalMoveInterface>();
        for (final BoardCellInterface[] line : this.getBoard()) {
            for (final BoardCellInterface cell : line) {
                if (cell.isEmpty()) { // TODO ? isPlayable() ou canPlayHere()
                    legalMoves.add(this.makeMove(side, cell.getPosition()));
                }
            }
        }
        return legalMoves;
    }

    // ------------------------------------------------------------
    @Override
    public boolean isGameOverFromVictory(final LegalMoveInterface justPlayedMove) {
        boolean isGameOverFromVictory = false;
        for (final BoardPlane plane : BoardPlane.values()) {
            final int connections = this.computeRealConnection(justPlayedMove,
                    plane.getOneWay())
                    + 1
                    + this.computeRealConnection(justPlayedMove,
                            plane.getOppositeWay());
            if (connections >= this.connections) {
                isGameOverFromVictory = true;
                break;
            }
        }
        return isGameOverFromVictory;
    }

    // ------------------------------------------------------------
    @Override
    public boolean isGameOverFromDraw(final LegalMoveInterface justPlayedMove) {
        return this.getLegalMoves(
                OpponentsEnumeration.opponent(justPlayedMove.getSide()))
                .isEmpty();
    }

    // ------------------------------------------------------------
    @Override
    public boolean doMove(final LegalMoveInterface moveToPlay) {
        final BoardCellInterface concernedCell = this.cell(moveToPlay.getPosition());
        concernedCell.setPiece(this.piece(moveToPlay.getSide()));
        return true;
    }

    // ------------------------------------------------------------
    @Override
    public boolean undoMove(final LegalMoveInterface move) {
        this.cell(move.getPosition()).setPiece(null); // TODO ? utiliser la pièce nulle
        return true; // is undo move complete ?
    }

    // ------------------------------------------------------------
    @Override
    public double computeStaticEvaluation(
            final LegalMoveInterface justPlayedMove) {

        double evaluation;

        if (this.isGameOverFromVictory(justPlayedMove)) {
            evaluation = Double.POSITIVE_INFINITY;
        }
        else
            if (this.isGameOverFromDraw(justPlayedMove)) {
                evaluation = 0.111;
            }
            else {
                //return this.computePotentialConnections(justPlayedMove);
                //return this.computeRealConnections(justPlayedMove);
                evaluation = 0;
                final int potentialConnections = this
                        .computePotentialConnections(justPlayedMove);
                final int realConnections = this
                        .computeRealConnections(justPlayedMove);
                if (potentialConnections > 0) {
                    final int n = (int) Math.log10(potentialConnections) + 1;
                    evaluation = potentialConnections / Math.pow(10, n);
                }
                evaluation += realConnections;
            }

        return evaluation;
    }

    // ------------------------------------------------------------
    protected int computeRealConnections(final LegalMoveInterface justPlayedMove) {
        int connections = 0;
        for (final BoardPlane plane : BoardPlane.values()) {
            connections = this.computeRealConnection(justPlayedMove,
                    plane.getOneWay())
                    + this.computeRealConnection(justPlayedMove,
                            plane.getOppositeWay());
        }
        return connections;
    }

    // ------------------------------------------------------------
    protected int computePotentialConnections(
            final LegalMoveInterface justPlayedMove) {
        int connections = 0;
        int connections1 = 0;
        int connections2 = 0;
        // ------------------------------------------------------------
        for (final BoardPlane plane : BoardPlane.values()) {
            // ------------------------------------------------------------
            connections1 = this.computePotentialConnection(justPlayedMove,
                    plane.getOneWay());
            connections2 = this.computePotentialConnection(justPlayedMove,
                    plane.getOppositeWay());
            // ------------------------------------------------------------
            connections += connections1 + connections2;
            // ------------------------------------------------------------
        }
        return connections;
    }

    // ------------------------------------------------------------
    protected int computeRealConnection(
            final LegalMoveInterface justPlayedMove,
            final BoardCardinalPosition direction) {
        int connected;
        BoardCellInterface cell = this.cell(justPlayedMove.getPosition());
        for (connected = 1; connected < this.connections; ++connected) {
            cell = cell.getNeighbour(direction);
            if (cell.isNull() || cell.isEmpty()
                    || cell.getPiece().getSide() != justPlayedMove.getSide()) {
                break;
            }
        }
        return --connected;
    }

    // ------------------------------------------------------------
    protected int computePotentialConnection(
            final LegalMoveInterface justPlayedMove,
            final BoardCardinalPosition direction) {
        int connected;
        BoardCellInterface cell = this.cell(justPlayedMove.getPosition());
        for (connected = 1; connected < this.connections; ++connected) {
            cell = cell.getNeighbour(direction);
            if (cell.isNull()) {
                break;
            }
            if (!cell.isEmpty()
                    && cell.getPiece().getSide() == OpponentsEnumeration
                            .opponent(justPlayedMove.getSide())) {
                break;
            }
        }
        return --connected;
    }

    // ------------------------------------------------------------
    protected PieceInterface piece(final OpponentsEnumeration side) {
        return this.pieceFactory.getPiece(side, TictactoePieceTypes.PAWN);
    }
    // ------------------------------------------------------------
}