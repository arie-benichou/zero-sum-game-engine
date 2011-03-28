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

package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells;

import java.util.HashMap;
import java.util.Map;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.Board;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.GameBoardInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.cells.BoardCellInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.positions.Positions;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.positions.PositionsInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensionss.BoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensionss.Dimension;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensionss.cells.pieces.PieceInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions.BoardPositionInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.samples.tictactoe.TictactoePieceTypes;
import fr.designpattern.zerosumgames.samples.tictactoe.pieces.TictactoePiecePawn;

public class _Cells implements CellsInterface {

    // ---------------------------------------------------------------------
    private transient PositionsInterface positionFactory;

    private final void setBoardPositionFactory(final PositionsInterface positionFactory) {
        this.positionFactory = positionFactory;
    }

    public final PositionsInterface getBoardPositionFactory() {
        return this.positionFactory;
    }

    // ---------------------------------------------------------------------
    private Map<BoardPositionInterface, BoardCellInterface> gameBoardCells;

    private final void setGameBoardCells(final Map<BoardPositionInterface, BoardCellInterface> gameBoardCells) {
        this.gameBoardCells = gameBoardCells;
    }

    public final Map<BoardPositionInterface, BoardCellInterface> getAllCells() {
        return this.gameBoardCells;
    }

    // ---------------------------------------------------------------------
    private final transient BoardCellInterface nullCell;

    public BoardCellInterface getNullCell() {
        return this.nullCell;
    }

    // ---------------------------------------------------------------------
    private BoardCellInterface createCell(final BoardPositionInterface position) {
        return new Cell(position);
    }

    // ---------------------------------------------------------------------
    private final Map<BoardPositionInterface, BoardCellInterface> initializeBoardCells(final Map<BoardPositionInterface, BoardCellInterface> boardCells) {
        ///int n = 0;
        BoardCellInterface cell;
        ///int numberOfPositions = this.getBoardPositionFactory().getNumberOfPositions();
        ///int numberOfDigitsInNumberOfPositions = numberOfPositions == 0 ? 1: (int) Math.log10(Math.abs(numberOfPositions)) + 1;
        ///System.out.println("\nInitialisation des " + numberOfPositions + " cellules...");
        for (final BoardPositionInterface[] line : this.getBoardPositionFactory().getBoardPositions()) {
            for (final BoardPositionInterface position : line) {
                ///System.out.format("%0" + numberOfDigitsInNumberOfPositions+ "d : ", ++n);
                // TODO utiliser une méthode de création
                cell = this.createCell(position);
                ///System.out.print(cell + "\n");
                boardCells.put(position, cell);
            }
        }
        return boardCells;
    }

    // ---------------------------------------------------------------------
    public Cells(final PositionsInterface positionFactory) {
        this.setBoardPositionFactory(positionFactory);
        this.setGameBoardCells(this.initializeBoardCells(new HashMap<BoardPositionInterface, BoardCellInterface>(positionFactory.getNumberOfPositions())));

        this.nullCell = new Cell(this.getBoardPositionFactory().getNullPosition());
    }

    // ---------------------------------------------------------------------
    public BoardCellInterface cell(final BoardPositionInterface position) {
        return position.isNull() ? this.nullCell : this.gameBoardCells.get(position);
    }

    // =====================================================================
    private class Cell implements BoardCellInterface {

        // ---------------------------------------------------------------------
        private final BoardPositionInterface position;

        public final BoardPositionInterface getPosition() {
            return this.position;
        }

        // ---------------------------------------------------------------------
        public Cell(final BoardPositionInterface position) {
            this.position = position;
        }

        // ---------------------------------------------------------------------
        public Cell(final BoardPositionInterface position, final PieceInterface piece) {
            this(position);
            this.setPiece(piece);
        }

        // ---------------------------------------------------------------------        
        private Cell(final BoardCellInterface cellToClone) {
            this(cellToClone.getPosition(), cellToClone.getPiece());
        }

        // ---------------------------------------------------------------------
        @Override
        public BoardCellInterface clone() {
            return new Cell(this);
        }

        // ---------------------------------------------------------------------
        public boolean isNull() {
            return this.getPosition().isNull();
        }

        // ---------------------------------------------------------------------
        private PieceInterface piece = null;

        public final void setPiece(final PieceInterface piece) {

            // TODO ? utiliser la pièce nulle
            if (this.isNull()) {
                throw new RuntimeException();
            }

            this.piece = piece;
        }

        public final PieceInterface getPiece() {

            // TODO utiliser la pièce nulle
            if (this.isNull()) {
                throw new RuntimeException();
            }

            return this.piece;
        }

        // ---------------------------------------------------------------------
        public boolean isEmpty() {

            // TODO ? renvoyer true
            if (this.isNull()) {
                throw new RuntimeException();
            }

            return this.getPiece() == null;
        }

        // ---------------------------------------------------------------------
        private transient Map<BoardCardinalPosition, BoardCellInterface> neighbourhood = null;

        public Map<BoardCardinalPosition, BoardCellInterface> getNeighbourhood() {
            if (this.neighbourhood == null) {

                //System.out.println("this.neighbourhood == null");

                this.neighbourhood = new HashMap<BoardCardinalPosition, BoardCellInterface>(8);
                this.neighbourhood.put(BoardCardinalPosition.TOP, Cells.this.cell(Cells.this.getBoardPositionFactory().topOf(this.getPosition())));
                this.neighbourhood.put(BoardCardinalPosition.RIGHT, Cells.this.cell(Cells.this.getBoardPositionFactory().rightOf(this.getPosition())));
                this.neighbourhood.put(BoardCardinalPosition.BOTTOM, Cells.this.cell(Cells.this.getBoardPositionFactory().bottomOf(this.getPosition())));
                this.neighbourhood.put(BoardCardinalPosition.LEFT, Cells.this.cell(Cells.this.getBoardPositionFactory().leftOf(this.getPosition())));
                this.neighbourhood.put(BoardCardinalPosition.TOP_RIGHT, Cells.this.cell(Cells.this.getBoardPositionFactory().topRightOf(this.getPosition())));
                this.neighbourhood.put(BoardCardinalPosition.TOP_LEFT, Cells.this.cell(Cells.this.getBoardPositionFactory().topLeftOf(this.getPosition())));
                this.neighbourhood.put(BoardCardinalPosition.BOTTOM_RIGHT,
                        Cells.this.cell(Cells.this.getBoardPositionFactory().bottomRightOf(this.getPosition())));
                this.neighbourhood.put(BoardCardinalPosition.BOTTOM_LEFT,
                        Cells.this.cell(Cells.this.getBoardPositionFactory().bottomLeftOf(this.getPosition())));
            }
            return this.neighbourhood;
        }

        // ---------------------------------------------------------------------
        @Override
        public String toString() {
            return "[position=" + this.position + ", piece=" + this.piece + "]";
        }

        // ---------------------------------------------------------------------
        public BoardCellInterface getNeighbour(final BoardCardinalPosition key) {
            return this.getNeighbourhood().get(key);
        }

        // ---------------------------------------------------------------------
        public BoardCellInterface top() {
            return this.getNeighbour(BoardCardinalPosition.TOP);
        }

        public BoardCellInterface right() {
            return this.getNeighbour(BoardCardinalPosition.RIGHT);
        }

        public BoardCellInterface bottom() {
            return this.getNeighbour(BoardCardinalPosition.BOTTOM);
        }

        public BoardCellInterface left() {
            return this.getNeighbour(BoardCardinalPosition.LEFT);
        }

        public BoardCellInterface topRight() {
            return this.getNeighbour(BoardCardinalPosition.TOP_RIGHT);
        }

        public BoardCellInterface topLeft() {
            return this.getNeighbour(BoardCardinalPosition.TOP_LEFT);
        }

        public BoardCellInterface bottomRight() {
            return this.getNeighbour(BoardCardinalPosition.BOTTOM_RIGHT);
        }

        public BoardCellInterface bottomLeft() {
            return this.getNeighbour(BoardCardinalPosition.BOTTOM_LEFT);
        }
        // ---------------------------------------------------------------------
    }

    // =====================================================================

    public static void main(final String[] args) {

        // TODO créér CellsTest.java

        final BoardDimensions dimension = new BoardDimensions(1, 2, 1, 2);
        final PositionsInterface positionFactory = new Positions(dimension);
        final CellsInterface cellFactory = new Cells(positionFactory);
        final GameBoardInterface board = new Board(cellFactory);

        final BoardCellInterface cell1 = board.cell(1, 1);
        final BoardCellInterface cell2 = board.cell(1, 2);
        final BoardCellInterface cell3 = board.cell(2, 1);
        final BoardCellInterface cell4 = board.cell(2, 2);

        //TODO créer la NullPiece au sein du framework
        final PieceInterface piece1 = new TictactoePiecePawn(TictactoePieceTypes.PAWN, OpponentsEnumeration.FIRST_PLAYER);

        final PieceInterface piece2 = new TictactoePiecePawn(TictactoePieceTypes.PAWN, OpponentsEnumeration.SECOND_PLAYER);

        cell1.setPiece(piece1);
        cell3.setPiece(piece2);

        System.out.println(cell1);
        System.out.println(cell2);
        System.out.println(cell3);
        System.out.println(cell4);

        System.out.println("");

        final BoardCellInterface cell1Clone = cell1.clone();
        final BoardCellInterface cell2Clone = cell2.clone();
        final BoardCellInterface cell3Clone = cell3.clone();
        final BoardCellInterface cell4Clone = cell4.clone();

        cell1Clone.setPiece(piece2);
        cell3Clone.setPiece(piece1);

        System.out.println(cell1Clone);
        System.out.println(cell2Clone);
        System.out.println(cell3Clone);
        System.out.println(cell4Clone);

        System.out.println("");

        System.out.println(cell1.getNeighbourhood());

        System.out.println("");

        System.out.println(cell1Clone.getNeighbourhood());

    }

}