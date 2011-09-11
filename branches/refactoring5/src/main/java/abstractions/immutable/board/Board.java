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

package abstractions.immutable.board;

import java.util.Map;

import abstractions.immutable.board.cell.BoardCell;
import abstractions.immutable.board.cell.BoardCellInterface;
import abstractions.immutable.board.cell.piece.Piece;
import abstractions.immutable.board.cell.piece.PieceInterface;
import abstractions.immutable.board.cell.piece.side.Side;
import abstractions.immutable.board.cell.piece.type.PieceType;
import abstractions.immutable.board.cell.piece.type._Pawn;
import abstractions.immutable.board.cell.position.Position;
import abstractions.immutable.board.cell.position.PositionInterface;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

public class Board implements BoardInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final static BoardInterface NULL = new Board(new BoardCellInterface[0][0]);

    /*-------------------------------------8<-------------------------------------*/

    private final static int computeHashCode(final BoardCellInterface[][] cells) {

        int hashCode = 0;
        for (final BoardCellInterface[] row : cells) {
            for (final BoardCellInterface cell : row) {
                hashCode = 31 * hashCode + cell.hashCode();
            }
        }
        return hashCode;
    }

    /*-------------------------------------8<-------------------------------------*/

    private static final BoardCellInterface[][] copy(final BoardCellInterface[][] cells) {
        final int rows = cells.length;
        if (rows == 0) return cells;
        final int columns = cells[0].length;
        final BoardCellInterface[][] copy = new BoardCell[rows][columns];
        for (int y = 0; y < rows; ++y)
            for (int x = 0; x < columns; ++x)
                copy[y][x] = cells[y][x];
        return copy;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final static class Factory {

        private final static Map<Integer, BoardInterface> CACHE = Maps.newHashMap();

        public static BoardInterface get(final BoardCellInterface[][] cells) {
            if (cells.length == 0) return NULL;
            final int address = computeHashCode(cells);
            BoardInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new Board(copy(cells));
                CACHE.put(address, instance);
            }
            return instance;
        }
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int rows;

    public final int rows() {
        return this.rows;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int columns;

    public final int columns() {
        return this.columns;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int hashCode;

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final BoardCellInterface[][] cells;

    /*-------------------------------------8<-------------------------------------*/

    public static BoardInterface from(final BoardCellInterface[][] cells) {
        return Factory.get(cells);
    }

    private Board(final BoardCellInterface[][] cells) {
        this.rows = cells.length;
        this.columns = this.rows == 0 ? 0 : cells[0].length;
        this.cells = cells;
        this.hashCode = computeHashCode(cells); // TODO ? lazy init
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static BoardInterface from(final int rows, final int columns) {
        return from(rows, columns, Piece.NULL);
    }

    private final static BoardCellInterface[][] init(final int rows, final int columns, final PieceInterface defaultValue) {
        final BoardCellInterface[][] cells = new BoardCell[rows][columns];
        for (int row = 0; row < rows; ++row)
            for (int column = 0; column < columns; ++column)
                cells[row][column] = BoardCell.from(Position.from(row, column), defaultValue);
        return cells;
    }

    public static BoardInterface from(final int rows, final int columns, final PieceInterface defaultValue) {
        return new Board(init(rows, columns, defaultValue));
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public BoardInterface apply() {
        return this;
    }

    @Override
    public BoardInterface apply(final Map<PositionInterface, PieceInterface> value) {
        final BoardCellInterface[][] newCells = new BoardCellInterface[this.rows()][this.columns()];
        for (int y = 0; y < this.rows(); ++y)
            for (int x = 0; x < this.columns(); ++x)
                newCells[y][x] = this.cells[y][x].apply(value.get(Position.from(y, x)));
        return new Board(newCells);
    }

    /*-------------------------------------8<-------------------------------------*/

    public BoardCellInterface cell(final int row, final int column) {
        return this.cells[row][column];
    }

    @Override
    public BoardCellInterface cell(final PositionInterface address) {
        return this.cells[address.row()][address.column()];
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public final String toString() { // TODO appeler cell.render() qui appelle piece.render()
        final String lineSeparator = "\n" + " " + Strings.repeat("----", this.columns()) + "-" + "\n";
        final String columnSeparator = " | ";
        final StringBuilder sb = new StringBuilder();
        for (int y = 0; y < this.rows(); ++y) {
            sb.append(lineSeparator);
            for (int x = 0; x < this.columns(); ++x)
                sb.append(columnSeparator + this.cells[y][x]);
            sb.append(columnSeparator);
        }
        sb.append(lineSeparator);
        return sb.toString();
    }

    /*-------------------------------------8<-------------------------------------*/

    public static void main(final String[] args) {

        Board.from(1, 1);
        Board.from(1, 1);

        System.out.println();

        Board.from(1, 2);
        Board.from(1, 2);

        System.out.println();

        Board.from(1, 3);
        Board.from(1, 3);

        System.out.println();

        Board.from(1, 4);
        Board.from(1, 4);

        System.out.println();

        final long t0 = System.currentTimeMillis();
        Board.from(1, 1000000);
        final long t1 = System.currentTimeMillis();
        System.out.println(t1 - t0 + " ms");

        // TODO Piece Symbols (rendering mapping) Créer les ConsoleRenderer et utiliser toString pour debug 

        final PieceInterface p1 = Piece.from(Side.from(1), PieceType.from(_Pawn.class));
        final PieceInterface p2 = p1.apply(Side.from(-1));

        System.out.println(p1);
        System.out.println(p2);

        final Map<PieceInterface, String> symbols = Maps.newHashMap();
        symbols.put(p1, "x");
        symbols.put(p2, "o");

        System.out.println(symbols);

        System.out.println(symbols.get(p1));
        System.out.println(symbols.get(p2));

    }
}
