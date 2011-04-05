
package concretisations.othello;

import static abstractions.dimension.API.DimensionFactory.Dimension;
import abstractions.board.BoardBuilder;
import abstractions.board.BoardInterface;
import abstractions.side.SideInterface;
import abstractions.side.Sides;
import concretisations.othello.pieces.Pieces;


// TODO ! tests unitaires
public class Tests {

    public static void main(String[] args) {

        //--------------------------------------------------------------------        
        BoardInterface board;
        SideInterface side;
        //--------------------------------------------------------------------        
        board = new BoardBuilder(Pieces.class, Dimension(8, 8)).build();
        side = Sides.FIRST_SIDE;
        
        board.getCell(4, 4).setPiece(side, Pieces.PAWN);
        board.getCell(4, 5).setPiece(side.getNextSide(), Pieces.PAWN);
        
        board.getCell(5, 4).setPiece(side.getNextSide(), Pieces.PAWN);
        board.getCell(5, 5).setPiece(side, Pieces.PAWN);
        
        board.getLegalMutations(side);
        System.out.println(board);

        System.out.println("--------------------------------------------------------------------");

    }

}