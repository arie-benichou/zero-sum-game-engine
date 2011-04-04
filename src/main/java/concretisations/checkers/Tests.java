
package concretisations.checkers;

import static abstractions.dimension.API.DimensionFactory.Dimension;
import abstractions.board.BoardBuilder;
import abstractions.board.BoardInterface;
import abstractions.side.SideInterface;
import abstractions.side.Sides;
import concretisations.checkers.pieces.Pieces;

// TODO ! tests unitaires
public class Tests {

    public static void main(String[] args) {

        //--------------------------------------------------------------------        
        BoardInterface board;
        SideInterface side;
        //--------------------------------------------------------------------        

        board = new BoardBuilder(Pieces.class, Dimension(5, 5)).build();
        side = Sides.FIRST_SIDE;

        board.getCell(4, 1).setPiece(side, Pieces.MAN);
        board.getCell(4, 4).setPiece(side, Pieces.MAN);
        board.getCell(3, 3).setPiece(side.getNextSide(), Pieces.MAN);

        board.getLegalMutations(side);

        System.out.println(board);

        //--------------------------------------------------------------------

        board = new BoardBuilder(Pieces.class, Dimension(5, 5)).build();
        side = Sides.FIRST_SIDE;

        
        board.getCell(4, 1).setPiece(side, Pieces.MAN);
        board.getCell(4, 4).setPiece(side, Pieces.MAN);

        board.getLegalMutations(side);

        System.out.println(board);

        //--------------------------------------------------------------------

        board = new BoardBuilder(Pieces.class, Dimension(5, 5)).build();
        side = Sides.FIRST_SIDE;

        board.getCell(4, 2).setPiece(side, Pieces.MAN);
        board.getCell(4, 4).setPiece(side, Pieces.MAN);
        board.getCell(3, 3).setPiece(side.getNextSide(), Pieces.MAN);

        board.getLegalMutations(side);

        System.out.println(board);

        //--------------------------------------------------------------------

        board = new BoardBuilder(Pieces.class, Dimension(5, 5)).build();
        side = Sides.FIRST_SIDE;

        board.getCell(3, 1).setPiece(side, Pieces.MAN);
        board.getCell(4, 2).setPiece(side, Pieces.MAN);
        board.getCell(4, 4).setPiece(side, Pieces.MAN);
        board.getCell(3, 3).setPiece(side.getNextSide(), Pieces.MAN);

        board.getLegalMutations(side);

        System.out.println(board);

        //--------------------------------------------------------------------

    }

}