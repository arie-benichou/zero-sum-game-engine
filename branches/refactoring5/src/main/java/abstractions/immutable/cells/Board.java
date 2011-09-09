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

package abstractions.immutable.cells;

import java.util.Map;

import abstractions.immutable.pieces.Piece;
import abstractions.immutable.pieces.PieceInterface;
import abstractions.immutable.positions.Position;
import abstractions.immutable.positions.PositionInterface;
import abstractions.immutable.side.Side;

import com.google.common.base.Strings;

public class Board implements BoardInterface {

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

    private final BoardCellInterface[][] cells;

    /*-------------------------------------8<-------------------------------------*/

    private final void init(final PieceInterface defaultPiece) {
        for (int row = 0; row < this.rows(); ++row)
            for (int column = 0; column < this.columns(); ++column)
                this.cells[row][column] = BoardCell.from(Position.from(row, column), defaultPiece);
    }

    private Board(final int rows, final int columns, final PieceInterface defaultPiece) {
        this.rows = rows;
        this.columns = columns;
        this.cells = new BoardCell[this.rows()][this.columns()];
        this.init(defaultPiece);
    }

    public static BoardInterface from(final int rows, final int columns, final PieceInterface defaultPiece) {
        return new Board(rows, columns, defaultPiece);
    }

    /*-------------------------------------8<-------------------------------------*/

    private Board(final int rows, final int columns) {
        this(rows, columns, Piece.from(Side.from(0), null));
    }

    public static BoardInterface from(final int rows, final int columns) {

        // TODO ??? utiliser Side.from(NullSide.class) pour expliciter la demande null object
        // TODO ??? autoriser Side.from(null) à retourner le null object
        // TODO créer PieceType et utiliser PieceType.from(NullPieceType.class)

        return new Board(rows, columns, Piece.from(Side.from(0), null));

    }

    /*-------------------------------------8<-------------------------------------*/

    private void copy(final BoardCellInterface[][] cells) {
        for (int y = 0; y < this.rows(); ++y)
            for (int x = 0; x < this.columns(); ++x)
                this.cells[y][x] = cells[y][x];
    }

    private Board(final BoardCellInterface[][] cells) {
        this.rows = cells.length;
        this.columns = cells[0].length;
        this.cells = new BoardCell[this.rows()][this.columns()];
        this.copy(cells);
    }

    public static BoardInterface from(final BoardCellInterface[][] value) {
        return new Board(value);
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
    public final String toString() {
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

}