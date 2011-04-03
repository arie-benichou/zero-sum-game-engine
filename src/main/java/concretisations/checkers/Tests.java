
package concretisations.checkers;

import static abstractions.board.API.BoardFactory.*;
import abstractions.board.API.BoardInterface;
import abstractions.side.API.SideInterface;
import concretisations.checkers.pieces.Man;

// TODO ! tests unitaires
public class Tests {

    public static void main(String[] args) {

        BoardInterface board;
        SideInterface sideToPlay;

        //--------------------------------------------------------------------        

        board = Board(5, 5);
        sideToPlay = abstractions.side.API.FIRST_SIDE;

        board.getCell(4, 1).setPiece(new Man(sideToPlay));
        board.getCell(4, 4).setPiece(new Man(sideToPlay));
        board.getCell(3, 3).setPiece(new Man(sideToPlay.getNextSide()));

        board.getLegalMutations(sideToPlay);

        System.out.println(board);

        //--------------------------------------------------------------------

        board = Board(5, 5);

        sideToPlay = abstractions.side.API.FIRST_SIDE;

        board.getCell(4, 1).setPiece(new Man(sideToPlay));
        board.getCell(4, 4).setPiece(new Man(sideToPlay));

        board.getLegalMutations(sideToPlay);

        System.out.println(board);

        //--------------------------------------------------------------------

        board = Board(5, 5);
        sideToPlay = abstractions.side.API.FIRST_SIDE;

        board.getCell(4, 2).setPiece(new Man(sideToPlay));
        board.getCell(4, 4).setPiece(new Man(sideToPlay));
        board.getCell(3, 3).setPiece(new Man(sideToPlay.getNextSide()));

        board.getLegalMutations(sideToPlay);

        System.out.println(board);

        //--------------------------------------------------------------------

        board = Board(5, 5);
        sideToPlay = abstractions.side.API.FIRST_SIDE;

        board.getCell(3, 1).setPiece(new Man(sideToPlay));
        board.getCell(4, 2).setPiece(new Man(sideToPlay));
        board.getCell(4, 4).setPiece(new Man(sideToPlay));
        board.getCell(3, 3).setPiece(new Man(sideToPlay.getNextSide()));

        board.getLegalMutations(sideToPlay);

        System.out.println(board);

        //--------------------------------------------------------------------

    }

}