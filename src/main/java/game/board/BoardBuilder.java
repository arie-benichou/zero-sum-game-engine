
package game.board;

import game.board.cells.Cells;
import game.board.dimensions.Dimensions;
import game.board.pieces.Piece;
import game.board.positions.Positions;

import java.util.ArrayList;

import opponents.Side;

import com.google.common.collect.Lists;

public class BoardBuilder {

    private transient int numberOfRows = 3;
    private transient int numberOfColumns = 3;

    public BoardBuilder() {}

    public final BoardBuilder rows(final int numberOfRows) {
        this.numberOfRows = numberOfRows;
        return this;
    }

    public final BoardBuilder columns(final int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
        return this;
    }

    public final BoardBuilder dimension(final int numberOfRows, final int numberOfColumns) {
        this.rows(numberOfRows);
        this.columns(numberOfColumns);
        return this;
    }

    public BoardInterface build() {
        return new Board(Cells.Factory.Cells(Positions.Factory.Positions(Dimensions.Factory.Dimension(this.numberOfRows, this.numberOfColumns))));
    }

    public BoardInterface clone(final BoardInterface board) {
        final ArrayList<Cells.Interface> cells = Lists.newArrayList();
        for (final Cells.Interface cell : board) {
            cells.add(Cells.Factory.Clone(cell));
        }
        return new Board(cells);
    }

    public static void main(final String[] args) {

        final BoardBuilder builder = new BoardBuilder();

        final BoardInterface board1 = builder.rows(2).columns(2).build();
        board1.cell(1, 1).setPiece(new Piece(Side.SECOND_PLAYER));
        board1.cell(2, 2).setPiece(new Piece(Side.SECOND_PLAYER));

        System.out.println(board1);

        final BoardInterface board2 = builder.clone(board1);
        System.out.println(board2);

        board2.cell(1, 1).setPiece(new Piece(Side.FIRST_PLAYER));
        board2.cell(2, 2).setPiece(new Piece(Side.FIRST_PLAYER));
        System.out.println(board2);

        System.out.println(board1);

    }
}