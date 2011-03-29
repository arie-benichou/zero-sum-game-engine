
package game.board;

import static game.cell.API.*;
import static game.cell.API.CellFactory.*;
import static game.dimension.API.DimensionFactory.*;
import static game.piece.API.PieceFactory.*;
import static game.position.API.PositionFactory.*;

import game.opponents.Side;

import java.util.ArrayList;


import com.google.common.collect.Lists;


//TODO plus de boardbuilder mais board (factory, interface, et constante...)
//TODO mettre au singulier chaque composant
//TODO renommer la classe pluriel en API


class BoardBuilder {

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
        return new Board(Cells(Positions(Dimension(this.numberOfRows, this.numberOfColumns))));
    }

    public BoardInterface clone(final BoardInterface board) {
        final ArrayList<CellInterface> cells = Lists.newArrayList();
        for (final  CellInterface cell : board) {
            cells.add(CellFactory.Clone(cell));
        }
        return new Board(cells);
    }

    public static void main(final String[] args) {

        // TODO écrire tests unitaires

        final BoardBuilder builder = new BoardBuilder();

        final BoardInterface board1 = builder.rows(2).columns(2).build();
        board1.cell(1, 1).setPiece(Piece(Side.SECOND_PLAYER));
        board1.cell(2, 2).setPiece(Piece(Side.SECOND_PLAYER));

        System.out.println(board1);

        final BoardInterface board2 = builder.clone(board1);
        System.out.println(board2);

        board2.cell(1, 1).setPiece(Piece(Side.FIRST_PLAYER));
        board2.cell(2, 2).setPiece(Piece(Side.FIRST_PLAYER));
        System.out.println(board2);

        System.out.println(board1);

    }
}