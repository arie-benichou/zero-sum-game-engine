/*
 * Copyright (C) 2011 Arié Bénichou
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

package fr.designpattern.zerosumgames.framework.service.gameplay.game.board;

import java.util.Map;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.cells.BoardCellInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.positions.Positions;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.positions.PositionsInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensionss.Dimension;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensionss.DimensionInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.pieces.PieceInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions.BoardPositionInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.samples.tictactoe.TictactoePieceTypes;
import fr.designpattern.zerosumgames.samples.tictactoe.pieces.TictactoePiecePawn;

public class _Board implements GameBoardInterface {

    private final BoardDimensionsInterface dimension;

    private final Map<BoardPositionInterface, BoardCellInterface> cells;

    /*
    @Override
    public BoardInterface clone() {

        final CellInterface[][] clonedBoard = this.createBoard();

        PositionInterface position;

        for (final CellInterface[] line : this) {
            for (final CellInterface cellToClone : line) {
                position = cellToClone.getPosition();
                clonedBoard[position.getInternalRowIndex()][position.getInternalColumnIndex()] = cellToClone.clone();
            }
        }

        //TODO revoir la nécessité d'injecter la cellFactory au board;        
        return new Board(clonedBoard, this.getBoardCellFactory());

    }
    */

    // ---------------------------------------------------------------------
    //TODO revoir la nécessité d'injecter la cellFactory au board;    
    public Board(final Map<BoardPositionInterface, BoardCellInterface> cells) {
        System.out.println("\nInitialisation du board...");
        this.cells = cells;
    }

    // ---------------------------------------------------------------------
    /*
    private Board(final CellInterface[][] board, final CellsInterface cellFactory) {
        //TODO revoir la nécessité d'injecter la cellFactory au board;
        this.setBoardCellFactory(cellFactory);
        this.cells = board;
    }
    */
    // ---------------------------------------------------------------------
    /*
    public CellInterface cell(final PositionInterface position) {
        CellInterface cell;
        
        if (position == null) {
            cell = this.getBoardCellFactory().getNullCell();
        }
        // TODO ? faire pareil que si position == null
        else
            if (position.isNull()) {
                cell = this.getBoardCellFactory().cell(position);
            }
            else {
                cell = this.cells[position.getInternalRowIndex()][position.getInternalColumnIndex()];
            }
        return cell;
    }
    */

    public BoardCellInterface cell(final BoardPositionInterface position) {
        BoardCellInterface cell;

        if (position == null) {
            cell = this.getBoardCellFactory().getNullCell();
        }
        // TODO ? faire pareil que si position == null
        else if (position.isNull()) {
            cell = this.getBoardCellFactory().cell(position);
        }
        else {
            cell = this.cells[position.getInternalRowIndex()][position.getInternalColumnIndex()];
        }
        return cell;
    }

    /*
    // ---------------------------------------------------------------------
    public CellInterface cell(final int clientRowIndex, final int clientColumnIndex) {
        return this.cell(this.getBoardCellFactory().getBoardPositionFactory().position(clientRowIndex, clientColumnIndex));
    }

    // ---------------------------------------------------------------------
    public Iterator<CellInterface[]> iterator() {
        return Arrays.asList(this.cells).iterator();
    }
    */
    // ---------------------------------------------------------------------
    @Override
    @SuppressWarnings("unused")
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        PieceInterface piece;
        for (final BoardCellInterface[] line : this.cells) {
            sb.append("\n");
            sb.append("-");
            for (final BoardCellInterface cell : line) {
                sb.append("----");
            }
            sb.append("\n");
            sb.append("|");
            for (final BoardCellInterface cell : line) {
                piece = cell.getPiece();
                sb.append(" " + (piece == null ? " " : piece) + " ");
                sb.append("|");
            }
        }
        sb.append("\n");
        sb.append("-");
        for (final BoardCellInterface cell : this.cells[0]) {
            sb.append("----");
        }
        return sb.toString();
    }

    public static void main(final String[] args) {

        // TODO créér BoardTest.java

        final BoardDimensions dimension = new BoardDimensions(1, 2, 1, 2);
        final PositionsInterface positionFactory = new Positions(dimension);
        final CellsInterface cellFactory = new Cells(positionFactory);
        final GameBoardInterface board = new Board(cellFactory);

        //TODO créer la NullPiece au sein du framework
        final PieceInterface piece1 = new TictactoePiecePawn(TictactoePieceTypes.PAWN, OpponentsEnumeration.FIRST_PLAYER);

        final PieceInterface piece2 = new TictactoePiecePawn(TictactoePieceTypes.PAWN, OpponentsEnumeration.SECOND_PLAYER);

        System.out.println(piece1);
        System.out.println(piece2);

        System.out.println(board);

        final GameBoardInterface clonedBoard = board.clone();

        clonedBoard.cell(1, 1).setPiece(piece1);

        //System.out.println(clonedBoard.cell(1, 1));

        System.out.println(clonedBoard);

        System.out.println(board);

    }

}