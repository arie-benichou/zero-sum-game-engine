
package concretisations.tictactoe;

import static abstractions.dimension.API.DimensionFactory.Dimension;

import java.util.List;

import abstractions.board.BoardBuilder;
import abstractions.board.BoardInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;
import abstractions.side.Sides;
import concretisations.tictactoe.pieces.Pieces;

// TODO ! tests unitaires
public class Tests {

    public static void main(String[] args) {

        //--------------------------------------------------------------------        
        BoardInterface board;
        SideInterface side;
        List<MutationInterface> legalMutations = null;
        //--------------------------------------------------------------------        
        board = new BoardBuilder(Pieces.class, Dimension(5, 5)).build();
        side = Sides.FIRST;

        board.getCell(4, 1).setPiece(side, Pieces.PAWN);
        board.getCell(4, 4).setPiece(side, Pieces.PAWN);
        board.getCell(3, 3).setPiece(side.getNextSide(), Pieces.PAWN);

        legalMutations = board.getLegalMutations(side);
        System.out.println(board);

        System.out.println("--------------------------------------------------------------------");

        //--------------------------------------------------------------------

    }

}