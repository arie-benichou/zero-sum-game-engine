
package game.board;

import game.board.cells.Cells;
import game.board.dimensions.Dimensions;
import game.board.dimensions.DimensionsInterface;
import game.board.pieces.Piece;
import game.board.positions.Positions;

import java.util.ArrayList;

import opponents.Side;

public class BoardBuilder {

    private transient DimensionsInterface dimensions;

    public BoardBuilder() {}

    public final BoardBuilder dimensions(final DimensionsInterface dimensions) {
        this.dimensions = dimensions;
        return this;
    }

    public BoardInterface build() {
        if (this.dimensions == null) {
            this.dimensions = new Dimensions(3, 3);
        }
        return new Board(Cells.Factory.Cells(Positions.Factory.Positions(this.dimensions)));
    }

    public BoardInterface clone(final BoardInterface board) {
        final ArrayList<Cells.Interface> cells = new ArrayList<Cells.Interface>();
        for (final Cells.Interface cell : board) {
            cells.add(Cells.Factory.Clone(cell));
        }
        return new Board(cells);
    }

    public static void main(final String[] args) {

        final BoardBuilder builder = new BoardBuilder();

        final BoardInterface board1 = builder.dimensions(new Dimensions(2, 2)).build();
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