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

import java.util.Arrays;
import java.util.Iterator;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.Dimension;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.DimensionInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.Cells;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.CellsInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.pieces.PieceInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.positions.PositionInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.positions.Positions;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.positions.PositionsInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.samples.tictactoe.TictactoePieceTypes;
import fr.designpattern.zerosumgames.samples.tictactoe.pieces.TictactoePiecePawn;

// TODO ? utiliser BoardCell et Equals
public class Board implements BoardInterface {

    // ---------------------------------------------------------------------
    private CellsInterface boardCellFactory;

    private final CellsInterface getBoardCellFactory() {
        return this.boardCellFactory;
    }

    private final void setBoardCellFactory(final CellsInterface boardCellFactory) {
        this.boardCellFactory = boardCellFactory;
    }

    // ---------------------------------------------------------------------
    private final CellInterface[][] board;

    private final CellInterface[][] getBoard() {
        return this.board;
    }

    // ---------------------------------------------------------------------
    // TODO ? utiliser boardDimension
    private CellInterface[][] createBoard() {

        final DimensionInterface dimension = this.getBoardCellFactory()
                .getBoardPositionFactory().getBoardDimension();
        /*
         * int[] byRow = new int[bd.getRowIndexRangeSize()];
         * 
         * for (IGameBoardPosition[] line :
         * this.getBoardCellFactory().getBoardPositionFactory
         * ().getBoardPositions()) for (IGameBoardPosition position : line) if
         * (!position.isNull()) ++byRow[position.getInternalRowIndex()];
         * 
         * int xMax = 0; for(int x : byRow) if(x > xMax) xMax = x;
         * 
         * return new IGameBoardCell[byRow.length][xMax];
         */

        //TODO à optimiser plus tard
        return new CellInterface[dimension.getRowIndexRangeSize()][dimension
                .getColumnIndexRangeSize()];
    }

    // ---------------------------------------------------------------------
    private CellInterface[][] initializeBoard(final CellInterface[][] board) {
        for (final PositionInterface position : this.getBoardCellFactory()
                .getAllCells().keySet()) {
            board[position.getInternalRowIndex()][position
                    .getInternalColumnIndex()] = this.getBoardCellFactory()
                    .cell(position);
        }
        return board;
    }

    // ---------------------------------------------------------------------
    @Override
    public BoardInterface clone() {

        final CellInterface[][] clonedBoard = this.createBoard();

        PositionInterface position;

        for (final CellInterface[] line : this) {
            for (final CellInterface cellToClone : line) {
                position = cellToClone.getPosition();
                clonedBoard[position.getInternalRowIndex()][position
                        .getInternalColumnIndex()] = cellToClone.clone();
            }
        }

        //TODO revoir la nécessité d'injecter la cellFactory au board;        
        return new Board(clonedBoard, this.getBoardCellFactory());

    }

    // ---------------------------------------------------------------------
    //TODO revoir la nécessité d'injecter la cellFactory au board;    
    public Board(final CellsInterface cellFactory) {
        ///System.out.println("\nInitialisation du board...");
        this.setBoardCellFactory(cellFactory);
        this.board = this.initializeBoard(this.createBoard());
    }

    // ---------------------------------------------------------------------    
    private Board(final CellInterface[][] board,
            final CellsInterface cellFactory) {
        //TODO revoir la nécessité d'injecter la cellFactory au board;
        this.setBoardCellFactory(cellFactory);
        this.board = board;
    }

    // ---------------------------------------------------------------------
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
                cell = this.getBoard()[position.getInternalRowIndex()][position
                        .getInternalColumnIndex()];
            }
        return cell;
    }

    // ---------------------------------------------------------------------
    public CellInterface cell(final int clientRowIndex,
            final int clientColumnIndex) {
        return this.cell(this.getBoardCellFactory()
                .getBoardPositionFactory()
                .position(clientRowIndex, clientColumnIndex));
    }

    // ---------------------------------------------------------------------
    public Iterator<CellInterface[]> iterator() {
        return Arrays.asList(this.getBoard()).iterator();
    }

    // ---------------------------------------------------------------------
    @Override
    @SuppressWarnings("unused")
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        PieceInterface piece;
        for (final CellInterface[] line : this.getBoard()) {
            sb.append("\n");
            sb.append("-");
            for (final CellInterface cell : line) {
                sb.append("----");
            }
            sb.append("\n");
            sb.append("|");
            for (final CellInterface cell : line) {
                piece = cell.getPiece();
                sb.append(" " + (piece == null ? " " : piece) + " ");
                sb.append("|");
            }
        }
        sb.append("\n");
        sb.append("-");
        for (final CellInterface cell : this.getBoard()[0]) {
            sb.append("----");
        }
        //sb.append("\n");
        return sb.toString();
    }

    // ---------------------------------------------------------------------

    public static void main(final String[] args) {

        // TODO créér BoardTest.java

        final Dimension dimension = new Dimension(1, 2, 1, 2);
        final PositionsInterface positionFactory = new Positions(dimension);
        final CellsInterface cellFactory = new Cells(positionFactory);
        final BoardInterface board = new Board(cellFactory);

        //TODO créer la NullPiece au sein du framework
        final PieceInterface piece1 = new TictactoePiecePawn(
                TictactoePieceTypes.PAWN,
                OpponentsEnumeration.FIRST_PLAYER);

        final PieceInterface piece2 = new TictactoePiecePawn(
                TictactoePieceTypes.PAWN,
                OpponentsEnumeration.SECOND_PLAYER);

        System.out.println(piece1);
        System.out.println(piece2);

        System.out.println(board);

        final BoardInterface clonedBoard = board.clone();

        clonedBoard.cell(1, 1).setPiece(piece1);

        //System.out.println(clonedBoard.cell(1, 1));

        System.out.println(clonedBoard);

        System.out.println(board);

    }
    // ---------------------------------------------------------------------   

}