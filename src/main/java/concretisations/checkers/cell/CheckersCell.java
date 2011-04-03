
package concretisations.checkers.cell;

import java.util.Set;

import concretisations.checkers.piece.CheckerPiece;
import concretisations.checkers.piece.Man;

import abstractions.board.API.BoardInterface;
import abstractions.board.BoardFactory;
import abstractions.cell.API.CellInterface;
import abstractions.cell.AbstractCell;
import abstractions.piece.PieceFactory;
import abstractions.position.API.PositionInterface;
import abstractions.position.RelativePosition;
import abstractions.side.API.SideInterface;

public class CheckersCell extends AbstractCell {

    public CheckersCell(PositionInterface position) {
        super(position);
    }
    
    // TODO la piece doit retourner une collection d'objets Mutation
    private Set<RelativePosition> getAvailableMutations(SideInterface side) {
        
        CheckerPiece piece = (CheckerPiece)this.getPiece();
        
        Set<RelativePosition> options = piece.getOptions(this);
        
        System.out.println("Available mutations for cell [" + this.getRow() + "][" + this.getColumn() + "]");
        System.out.println(options);
        
        return options;
        
    }
    
    private boolean hasAvailableMutations(SideInterface side) {
        return !this.getAvailableMutations(side).isEmpty();
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
            this.isMutable = this.hasAvailableMutations(side); 
        }
        return this.isMutable; 
        
    }

    @Override
    public final String toString() {
        return this.isMutable ? "(" + this.getPiece() + ")|" : super.toString();
    }
    
    // TODO à intégrer dans les tests unitaires
    public static void main(String[] args) {

        BoardInterface board;
        BoardFactory boardFactory;
        SideInterface sideToPlay;
        Set<CellInterface> mutableCells;
        
        
        System.out.println("--------------------------------------------------------------------");
        
        
        boardFactory = new BoardFactory(CheckersCell.class);
        board = boardFactory.Board(5, 5);
        
        sideToPlay = abstractions.side.API.FIRST_SIDE;
        
        board.getCell(4, 2).setPiece(new Man(sideToPlay));
        board.getCell(4, 4).setPiece(new Man(sideToPlay));
        
        mutableCells = board.getMutableCells(sideToPlay);
        
        System.out.println(board);
                

        System.out.println("--------------------------------------------------------------------");
        
        
        boardFactory = new BoardFactory(CheckersCell.class);
        board = boardFactory.Board(5, 5);
        
        sideToPlay = abstractions.side.API.FIRST_SIDE;
        
        board.getCell(4, 2).setPiece(new Man(sideToPlay));
        board.getCell(4, 4).setPiece(new Man(sideToPlay));
        board.getCell(3, 3).setPiece(new Man(sideToPlay.getNextSide()));
        
        mutableCells = board.getMutableCells(sideToPlay);
        
        System.out.println(board);
        
        
        System.out.println("--------------------------------------------------------------------");
        
        
        boardFactory = new BoardFactory(CheckersCell.class);
        board = boardFactory.Board(5, 5);
        
        sideToPlay = abstractions.side.API.FIRST_SIDE;
        
        board.getCell(4, 1).setPiece(new Man(sideToPlay));
        board.getCell(4, 4).setPiece(new Man(sideToPlay));
        board.getCell(3, 3).setPiece(new Man(sideToPlay.getNextSide()));
        
        mutableCells = board.getMutableCells(sideToPlay);
        
        System.out.println(board);
        
        
        System.out.println("--------------------------------------------------------------------");
        
        
        
    }

}