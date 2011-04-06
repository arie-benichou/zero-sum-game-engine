
package concretisations.connect4;

import static abstractions.dimension.API.DimensionFactory.Dimension;

import java.util.List;

import abstractions.board.BoardBuilder;
import abstractions.board.BoardInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;
import abstractions.side.Sides;
import concretisations.connect4.pieces.Pieces;

// TODO ! tests unitaires
public class Tests {

    public static void main(String[] args) {

        //--------------------------------------------------------------------        
        BoardInterface board;
        SideInterface side;
        List<MutationInterface> legalMutations = null;
        //--------------------------------------------------------------------        
        board = new BoardBuilder(Pieces.class, Dimension(5, 6)).build();
        side = Sides.FIRST;

        board.getCell(5, 2).setPiece(side, Pieces.PAWN);
        
        board.getCell(4, 3).setPiece(side.getNextSide(), Pieces.PAWN);
        board.getCell(5, 3).setPiece(side, Pieces.PAWN);
        
        board.getCell(3, 4).setPiece(side, Pieces.PAWN);
        board.getCell(4, 4).setPiece(side.getNextSide(), Pieces.PAWN);
        board.getCell(5, 4).setPiece(side, Pieces.PAWN);
        
        board.getCell(2, 5).setPiece(side.getNextSide(), Pieces.PAWN);
        board.getCell(3, 5).setPiece(side.getNextSide(), Pieces.PAWN);
        board.getCell(4, 5).setPiece(side.getNextSide(), Pieces.PAWN);
        board.getCell(5, 5).setPiece(side.getNextSide(), Pieces.PAWN);
        
        board.getCell(1, 6).setPiece(side.getNextSide(), Pieces.PAWN);        
        board.getCell(2, 6).setPiece(side.getNextSide(), Pieces.PAWN);
        board.getCell(3, 6).setPiece(side.getNextSide(), Pieces.PAWN);
        board.getCell(4, 6).setPiece(side.getNextSide(), Pieces.PAWN);
        board.getCell(5, 6).setPiece(side.getNextSide(), Pieces.PAWN);                

        legalMutations = board.getLegalMutations(side);
        System.out.println(board);

        System.out.println("--------------------------------------------------------------------");

        //--------------------------------------------------------------------

    }

}