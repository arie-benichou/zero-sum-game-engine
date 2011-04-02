
package concretisations.checkers.cell;

import java.util.Set;

import abstractions.board.BoardFactory;
import abstractions.board.API.BoardInterface;
import abstractions.cell.API.CellInterface;
import abstractions.cell.AbstractCell;
import abstractions.piece.API.PieceFactory;
import abstractions.position.API.PositionInterface;
import abstractions.side.API.SideInterface;

public class CheckersCell extends AbstractCell {

    public CheckersCell(PositionInterface position) {
        super(position);
    }

    
    private boolean checkJump(SideInterface side) {
        
        boolean canJump = false;
        
        if(this.getNext(-1, 1).getPiece().getSide().getNextSide().equals(side) && this.getNext(-2, 2).isEmpty()) {
            canJump = true;
        }
        
        else if(this.getNext(-1, -1).getPiece().getSide().getNextSide().equals(side) && this.getNext(-2, -2).isEmpty()) {
            canJump = true;
        }        
        
        return canJump;
    }    

    @Override
    public boolean isMutable(SideInterface side) {
        
        if(this.isEmpty()) {
            this.isMutable = false;
        }
        
        else if(!this.getPiece().getSide().equals(side)) {
            this.isMutable = false;
        }
        else {
            this.isMutable = this.checkJump(side); 
        }
        return this.isMutable; 
        
    }

    @Override
    public final String toString() {
        return this.isMutable ? "(" + this.getPiece() + ")|" : super.toString();
    }
    
    
    public static void main(String[] args) {

        BoardInterface board;
        BoardFactory boardFactory;
        SideInterface sideToPlay = abstractions.side.API.FIRST_SIDE;        
        
        boardFactory = new BoardFactory(CheckersCell.class);
        board = boardFactory.Board(4, 4);
        
        board.getCell(4, 1).setPiece(PieceFactory.Piece(sideToPlay));
        board.getCell(4, 3).setPiece(PieceFactory.Piece(sideToPlay));
        board.getCell(3, 2).setPiece(PieceFactory.Piece(sideToPlay.getNextSide()));
        
        Set<CellInterface> mutableCells = board.getMutableCells(sideToPlay);
        
        System.out.println(board);
                
        System.out.println("--------------------------------------------------------------------");
        
        
    }

}