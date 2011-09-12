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

package abstractions.immutable.context.board;

import java.util.Map;

import rendering.board.BoardConsoleRendering;
import rendering.board.BoardRenderer;
import rendering.board.BoardRenderingInterface;
import rendering.board.BoardStringRendering;
import rendering.board.cell.BoardCellStringRendering;
import rendering.board.cell.piece.PieceStringRendering;
import abstractions.immutable.context.board.cell.BoardCell;
import abstractions.immutable.context.board.cell.BoardCellInterface;
import abstractions.immutable.context.board.cell.piece.Piece;
import abstractions.immutable.context.board.cell.piece.PieceInterface;
import abstractions.immutable.context.board.cell.piece.side.Side;
import abstractions.immutable.context.board.cell.piece.type.PieceType;
import abstractions.immutable.context.board.cell.piece.type._Pawn;
import abstractions.immutable.context.board.cell.position.Position;
import abstractions.immutable.context.board.cell.position.PositionInterface;
import abstractions.immutable.context.board.direction.Direction;
import abstractions.immutable.context.board.direction.DirectionInterface;
import abstractions.immutable.move.Move;
import abstractions.immutable.move.MoveInterface;
import abstractions.immutable.move.mutation.BoardMutation;
import abstractions.immutable.move.mutation.MutationInterface;
import abstractions.immutable.move.type.MoveType;
import abstractions.immutable.move.type._NewPawn;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

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

    public final static class Factory {

        private static int cacheHits;

        private final static Map<Integer, BoardInterface> CACHE = Maps.newHashMap();

        public static BoardInterface get(final BoardCellInterface[][] cells) {
            if (cells.length == 0) return NULL;
            final int address = computeHashCode(cells);
            BoardInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new Board(copy(cells));
                CACHE.put(address, instance);
            }
            else
                ++cacheHits;
            return instance;
        }

        public final static int size() {
            return CACHE.size();
        }

        public final static int cacheHits() {
            return cacheHits;
        }
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int rows;

    @Override
    public final int rows() {
        return this.rows;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int columns;

    @Override
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

    public static BoardInterface from(final int rows, final int columns, final PieceInterface defaultValue) {
        return new Board(init(rows, columns, defaultValue)); // TODO ?? utiliser la factory
    }

    public final static BoardInterface from(final int rows, final int columns) {
        return from(rows, columns, Piece.NULL);
    }

    private final static BoardCellInterface[][] init(final int rows, final int columns, final PieceInterface defaultValue) {
        final BoardCellInterface[][] cells = new BoardCell[rows][columns];
        for (int row = 0; row < rows; ++row)
            for (int column = 0; column < columns; ++column)
                cells[row][column] = BoardCell.from(Position.from(row + 1, column + 1), defaultValue);
        return cells;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public BoardInterface apply() {
        return this;
    }

    @Override
    public BoardInterface apply(final MutationInterface<PositionInterface, PieceInterface> mutation) {
        final BoardCellInterface[][] newCells = new BoardCellInterface[this.rows()][this.columns()];
        for (int y = 0; y < this.rows(); ++y)
            for (int x = 0; x < this.columns(); ++x)
                newCells[y][x] = this.cells[y][x].apply(mutation.value().get(Position.from(y + 1, x + 1)));
        // TODO ?? utiliser la factory
        //return Factory.get(newCells);
        return new Board(newCells);
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

    @Override
    public Map<DirectionInterface, BoardCellInterface> neighbourhoodOf(final PositionInterface position) { // TODO lazy init
        final Map<DirectionInterface, BoardCellInterface> neighbourhood = Maps.newHashMap();
        for (final DirectionInterface direction : Direction.ALL_AROUND) {
            neighbourhood.put(direction, this.cell(position.apply(direction)));
        }
        return neighbourhood;
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

        /*-------------------------------------8<-------------------------------------*/

        BoardInterface board = Board.from(100, 1000);

        /*-------------------------------------8<-------------------------------------*/

        Map<Object, Object> symbols = null;
        MoveInterface move = null;

        /*-------------------------------------8<-------------------------------------*/

        final long t0 = System.currentTimeMillis();
        for (int i = 0; i < 100; ++i) {

            /*-------------------------------------8<-------------------------------------*/

            final PieceInterface p0 = Piece.NULL;
            final PieceInterface p1 = Piece.from(Side.from(1), PieceType.from(_Pawn.class));
            final PieceInterface p2 = p1.apply(p1.side().opposite());

            /*-------------------------------------8<-------------------------------------*/

            symbols = Maps.newHashMap();
            symbols.put(p0, " ");
            symbols.put(p1, "x");
            symbols.put(p2, "o");

            /*-------------------------------------8<-------------------------------------*/

            final ImmutableSortedMap<PositionInterface, PieceInterface> map =
                    new ImmutableSortedMap.Builder<PositionInterface, PieceInterface>(Ordering.natural())
                            .put(Position.from(1, 1), Piece.from(Side.from(1), PieceType.from(_Pawn.class)))
                            .put(Position.from(1, 1).apply(Direction.from(1, 0)), Piece.from(Side.from(-1), PieceType.from(_Pawn.class)))
                            .build();

            move = Move.from(MoveType.from(_NewPawn.class), BoardMutation.from(map));

            /*-------------------------------------8<-------------------------------------*/

            board.apply(move.mutation());

            /*-------------------------------------8<-------------------------------------*/

        }

        /*-------------------------------------8<-------------------------------------*/

        final long t1 = System.currentTimeMillis();
        System.out.println(t1 - t0 + " ms");

        /*-------------------------------------8<-------------------------------------*/

        System.out.println("Board Factory.size : " + Board.Factory.cacheHits() + "/" + Board.Factory.size());
        System.out.println("BoardCell Factory.size : " + BoardCell.Factory.cacheHits() + "/" + BoardCell.Factory.size());
        System.out.println("Position Factory.size : " + Position.Factory.cacheHits() + "/" + Position.Factory.size());
        System.out.println("Direction Factory.size : " + Direction.Factory.cacheHits() + "/" + Direction.Factory.size());
        System.out.println("Piece Factory.size : " + Piece.Factory.cacheHits() + "/" + Piece.Factory.size());
        System.out.println("Side Factory.size : " + Side.Factory.cacheHits() + "/" + Side.Factory.size());
        System.out.println("PieceType Factory.size : " + PieceType.Factory.cacheHits() + "/" + PieceType.Factory.size());
        System.out.println("Move Factory.size : " + Move.Factory.cacheHits() + "/" + Move.Factory.size());
        System.out.println("MoveType Factory.size : " + MoveType.Factory.cacheHits() + "/" + MoveType.Factory.size());
        System.out.println("BoardMutation Factory.size : " + BoardMutation.Factory.cacheHits() + "/" + BoardMutation.Factory.size());

        /*-------------------------------------8<-------------------------------------*/

        final BoardRenderingInterface<?> renderingType =
                new BoardConsoleRendering(
                        new BoardStringRendering(new BoardCellStringRendering(
                                new PieceStringRendering())));

        final BoardRenderer boardRenderer = new BoardRenderer(renderingType); // TODO ? créer une interface

        /*-------------------------------------8<-------------------------------------*/

        board = Board.from(3, 3);
        boardRenderer.render(board, symbols);

        final BoardInterface newBoard = board.apply(move.mutation());
        boardRenderer.render(board, symbols);
        boardRenderer.render(newBoard, symbols);

        /*-------------------------------------8<-------------------------------------*/

    }
}
