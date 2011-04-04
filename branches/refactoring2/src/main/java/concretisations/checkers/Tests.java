
package concretisations.checkers;

import static abstractions.dimension.API.DimensionFactory.Dimension;
import static abstractions.side.API.FIRST_SIDE;
import abstractions.board.API.BoardInterface;
import abstractions.board.BoardBuilder;
import abstractions.side.API.SideInterface;
import concretisations.checkers.pieces.Pieces;

// TODO ! tests unitaires
public class Tests {

    public static void main(String[] args) {

        //--------------------------------------------------------------------        
        BoardInterface board;
        SideInterface side;
        //--------------------------------------------------------------------        

        board = new BoardBuilder(Pieces.class, Dimension(5, 5)).build();
        side = FIRST_SIDE;

        board.getCell(4, 1).setPiece(side, Pieces.MAN);
        board.getCell(4, 4).setPiece(side, Pieces.MAN);
        board.getCell(3, 3).setPiece(side.getNextSide(), Pieces.MAN);

        board.getLegalMutations(side);

        System.out.println(board);

        //--------------------------------------------------------------------

        board = new BoardBuilder(Pieces.class, Dimension(5, 5)).build();
        side = FIRST_SIDE;

        board.getCell(4, 1).setPiece(side, Pieces.MAN);
        board.getCell(4, 4).setPiece(side, Pieces.MAN);

        board.getLegalMutations(side);

        System.out.println(board);

        //--------------------------------------------------------------------

        board = new BoardBuilder(Pieces.class, Dimension(5, 5)).build();
        side = FIRST_SIDE;

        board.getCell(4, 2).setPiece(side, Pieces.MAN);
        board.getCell(4, 4).setPiece(side, Pieces.MAN);
        board.getCell(3, 3).setPiece(side.getNextSide(), Pieces.MAN);

        board.getLegalMutations(side);

        System.out.println(board);

        //--------------------------------------------------------------------

        board = new BoardBuilder(Pieces.class, Dimension(5, 5)).build();
        side = FIRST_SIDE;

        board.getCell(3, 1).setPiece(side, Pieces.MAN);
        board.getCell(4, 2).setPiece(side, Pieces.MAN);
        board.getCell(4, 4).setPiece(side, Pieces.MAN);
        board.getCell(3, 3).setPiece(side.getNextSide(), Pieces.MAN);

        board.getLegalMutations(side);

        System.out.println(board);

        //--------------------------------------------------------------------

    }

}