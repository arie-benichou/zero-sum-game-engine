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

package context.entity.game.board;

import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import context.entity.game.board.cell.BoardCell;
import context.entity.game.board.cell.BoardCellInterface;
import context.entity.game.board.cell.piece.Piece;
import context.entity.game.board.cell.piece.PieceInterface;
import context.entity.game.board.cell.position.Position;
import context.entity.game.board.cell.position.PositionInterface;
import context.entity.game.board.mutation.BoardMutation;
import context.entity.game.board.mutation.MutationInterface;

public final class Board implements BoardInterface {

    public static int instances = 0;

    /*-------------------------------------8<-------------------------------------*/

    //private final static BoardInterface NULL = new Board(0, 0, Piece.NULL);

    /*-------------------------------------8<-------------------------------------*/

    private final int rows;

    @Override
    public int rows() {
        return this.rows;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int columns;

    @Override
    public int columns() {
        return this.columns;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int numberOfCells;

    @Override
    public int numberOfCells() {
        return this.numberOfCells;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final BoardCellInterface[][] cells;

    /*-------------------------------------8<-------------------------------------*/

    private Map<PieceInterface, Integer> indexes = null;

    /*-------------------------------------8<-------------------------------------*/

    public static BoardInterface from(final BoardCellInterface[][] cells) {
        return new Board(cells);
    }

    public static BoardInterface from(final int rows, final int columns, final PieceInterface defaultValue) {
        return new Board(rows, columns, defaultValue);
    }

    public final static BoardInterface from(final int rows, final int columns) {
        return from(rows, columns, Piece.NULL);
    }

    public static BoardInterface from(final int rows, final int columns, final PieceInterface defaultValue, final BoardMutation mutation) {
        return from(rows, columns, defaultValue).apply(mutation);
    }

    /*-------------------------------------8<-------------------------------------*/

    private Board(final int rows, final int columns) {
        ++instances;
        this.rows = rows;
        this.columns = columns;
        this.numberOfCells = this.rows * this.columns;
        this.cells = new BoardCell[this.rows][this.columns];
    }

    public Board(final int rows, final int columns, final PieceInterface defaultValue) {
        this(rows, columns);
        for (int y = 0; y < rows; ++y)
            for (int x = 0; x < columns; ++x)
                this.cells[y][x] = BoardCell.from(Position.from(y + 1, x + 1), defaultValue);
        this.indexes = Maps.newHashMap();
        this.indexes.put(defaultValue, rows * columns);
    }

    public Board(final BoardCellInterface[][] cells) {
        this(cells.length, cells.length == 0 ? 0 : cells[0].length);
        for (int y = 0; y < this.rows; ++y)
            for (int x = 0; x < this.columns; ++x)
                this.cells[y][x] = cells[y][x];
    }

    public Board(final BoardCellInterface[][] cells, final MutationInterface<PositionInterface, PieceInterface> mutation) {
        this(cells.length, cells.length == 0 ? 0 : cells[0].length);
        for (int y = 0; y < this.rows; ++y)
            for (int x = 0; x < this.columns; ++x)
                this.cells[y][x] = cells[y][x].apply(mutation.value().get(Position.from(y + 1, x + 1)));
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public BoardInterface apply() {
        return this;
    }

    @Override
    public BoardInterface apply(final MutationInterface<PositionInterface, PieceInterface> mutation) {
        return mutation.isNull() ? this.apply() : new Board(this.cells, mutation);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public BoardCellInterface cell(final int row, final int column) {
        if (row < 1 || row > this.rows() || column < 1 || column > this.columns()) return BoardCell.NULL;
        return this.cells[row - 1][column - 1];
    }

    @Override
    public BoardCellInterface cell(final PositionInterface address) {
        return this.cell(address.row(), address.column());
    }

    /*-------------------------------------8<-------------------------------------*/

    private Map<PieceInterface, Integer> count() {
        final Map<PieceInterface, Integer> indexes = Maps.newHashMap();
        for (int y = 0; y < this.rows; ++y)
            for (int x = 0; x < this.columns; ++x) {
                final PieceInterface piece = this.cells[y][x].value();
                final Integer counter = indexes.get(piece);
                if (counter == null) indexes.put(piece, 1);
                else indexes.put(piece, counter + 1);
            }
        return indexes;
    }

    @Override
    public int count(final PieceInterface value) {
        if (this.indexes == null) this.indexes = this.count();
        final Integer count = this.indexes.get(value);
        return count == null ? 0 : count;
    }

    /*-------------------------------------8<-------------------------------------*/

    /*
    @Override
    public Map<DirectionInterface, BoardCellInterface> neighbourhoodOf(final PositionInterface position) {
        final Map<DirectionInterface, BoardCellInterface> neighbourhood = Maps.newHashMap();
        for (final DirectionInterface direction : Direction.ALL_AROUND) {
            neighbourhood.put(direction, this.cell(position.apply(direction)));
        }
        return neighbourhood;
    }
    */

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