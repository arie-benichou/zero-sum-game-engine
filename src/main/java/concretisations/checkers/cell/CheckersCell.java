
package concretisations.checkers.cell;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import abstractions.board.API.BoardInterface;
import abstractions.board.BoardFactory;
import abstractions.cell.API.CellInterface;
import abstractions.cell.AbstractCell;
import abstractions.cell.mutation.MutationInterface;
import abstractions.piece.API.PieceInterface;
import abstractions.position.API.PositionInterface;
import abstractions.side.API.SideInterface;
import concretisations.checkers.mutations.CheckersMutation;
import concretisations.checkers.mutations.JumpMutation;
import concretisations.checkers.mutations.WalkMutation;
import concretisations.checkers.pieces.CheckerPiece;
import concretisations.checkers.pieces.Man;


// TODO à virer, plus besoin d'avoir une factory de cell, également
public class CheckersCell extends AbstractCell {

    public CheckersCell(PositionInterface position) {
        super(position);
    }
    
    // TODO la piece doit retourner une collection d'objets Mutation : un Move correspond à un paquet de mutations (3 mutations de base).
    /*
    public Set<MutationInterface> fetchAvailableMutations(SideInterface side) { // TODO ? injecter un contexte
        // TODO renommer en getAvailableChainedMutations
        // TODO avoir une cellule universelle ?
        // TODO utiliser l'objet null d'une piece ?
        // TODO renommer evolution en alteration ?
        PieceInterface piece = this.getPiece();
        Set<MutationInterface> availableMutations = piece.computeAvailableMutations(this);
        System.out.println("Available mutations for cell [" + this.getRow() + "][" + this.getColumn() + "]");
        System.out.println(availableMutations);
        return availableMutations;
    }
    */
    
    /*
    private Set<MutationInterface> getAvailableMutations(SideInterface side) {
        Set<MutationInterface> availableMutations
        
        
    }
    */

    /*
    private boolean hasAvailableMutations(SideInterface side) {
        return !this.fetchAvailableMutations(side).isEmpty();
    }    

    @Override
    public boolean isMutable(SideInterface side) { // TODO ? injecter un contexte
        
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
    */

    /*
    public Set<MutationInterface> _filterAvailableMutations(Set<MutationInterface> availableMutations) {
        
        // TODO ? pouvoir trier les mutations
        
        // TODO ? créer un prédicat qui interdit l'ajout d'une WalkMutation si une JumpMutation existe
        
        // TODO créer un filtre qui enlève les WalkMutation si une JumpMutation existe
        
        boolean willHaveToJump = false;
        
        for(MutationInterface mutation : availableMutations) {
            if(mutation instanceof JumpMutation) {
                System.out.println("Jump détecté!");
                willHaveToJump = true;
                break;
            }
        }
        
        Set<MutationInterface> filteredAvailableMutations = Sets.newHashSet();
        
        if(willHaveToJump) {
            System.out.println("Je vérifie les mutations inhibées par la JUMP mutation...");
            System.out.println("\n");
            for(MutationInterface mutation : availableMutations) {
                
                System.out.println(mutation);
                
                if(!((CheckersMutation)mutation).isInhibited()) {
                    filteredAvailableMutations.add(mutation);
                }
                
            }
            System.out.println("\n");
        }
        else {
            for(MutationInterface mutation : availableMutations) {
                ((CheckerMutation)mutation).isInhibited(false);
            } 
            filteredAvailableMutations = availableMutations;
        }

        return filteredAvailableMutations;

    }    
    */

    /*
    @Override
    public final String toString() {
        return this.isMutable ? "(" + this.getPiece() + ")|" : super.toString();
    }
    */
    
    // TODO à intégrer dans les tests unitaires
    public static void main(String[] args) {

        BoardInterface board;
        BoardFactory boardFactory;
        SideInterface sideToPlay;
        //Set<CellInterface> mutableCells;
        List<MutationInterface> availableMutations;
        
        
        /*
        
        //System.out.println("--------------------------------------------------------------------");
        
        
        boardFactory = new BoardFactory(CheckersCell.class);
        board = boardFactory.Board(5, 5);
        
        sideToPlay = abstractions.side.API.FIRST_SIDE;
        
        board.getCell(4, 2).setPiece(new Man(sideToPlay));
        board.getCell(4, 4).setPiece(new Man(sideToPlay));
        
        System.out.println(board);
        
        //mutableCells = board.getMutableCells(sideToPlay);
        availableMutations = board.getAvailableMutations(sideToPlay);
        
        System.out.println(board);
                

        System.out.println("--------------------------------------------------------------------");
        
        
        boardFactory = new BoardFactory(CheckersCell.class);
        board = boardFactory.Board(5, 5);
        
        sideToPlay = abstractions.side.API.FIRST_SIDE;
        
        board.getCell(4, 2).setPiece(new Man(sideToPlay));
        board.getCell(4, 4).setPiece(new Man(sideToPlay));
        board.getCell(3, 3).setPiece(new Man(sideToPlay.getNextSide()));
        
        System.out.println(board);
        
        //mutableCells = board.getMutableCells(sideToPlay);
        availableMutations = board.getAvailableMutations(sideToPlay);
        
        System.out.println(board);
        
        
        System.out.println("--------------------------------------------------------------------");
        
        
        boardFactory = new BoardFactory(CheckersCell.class);
        board = boardFactory.Board(5, 5);
        
        sideToPlay = abstractions.side.API.FIRST_SIDE;
        
        board.getCell(4, 1).setPiece(new Man(sideToPlay));
        board.getCell(4, 4).setPiece(new Man(sideToPlay));
        board.getCell(3, 3).setPiece(new Man(sideToPlay.getNextSide()));
        
        System.out.println(board);
        
        //mutableCells = board.getMutableCells(sideToPlay);
        availableMutations = board.getAvailableMutations(sideToPlay);

        System.out.println(board);
        
        System.out.println("--------------------------------------------------------------------");
        
        */
        
        boardFactory = new BoardFactory(CheckersCell.class);
        board = boardFactory.Board(5, 5);
        
        sideToPlay = abstractions.side.API.FIRST_SIDE;
        
        board.getCell(4, 1).setPiece(new Man(sideToPlay));
        board.getCell(4, 4).setPiece(new Man(sideToPlay));
        board.getCell(3, 3).setPiece(new Man(sideToPlay.getNextSide()));

        System.out.println(board);
        
        availableMutations = board.getLegalMutations(sideToPlay);
        
        System.out.println(board);
        
        System.out.println("--------------------------------------------------------------------");
        
        
        
        boardFactory = new BoardFactory(CheckersCell.class);
        board = boardFactory.Board(5, 5);
        
        sideToPlay = abstractions.side.API.FIRST_SIDE;
        
        board.getCell(4, 1).setPiece(new Man(sideToPlay));
        board.getCell(4, 4).setPiece(new Man(sideToPlay));
        //board.getCell(3, 3).setPiece(new Man(sideToPlay.getNextSide()));

        System.out.println(board);
        
        availableMutations = board.getLegalMutations(sideToPlay);
        
        System.out.println(board);
        
        System.out.println("--------------------------------------------------------------------");
        
        
        
        boardFactory = new BoardFactory(CheckersCell.class);
        board = boardFactory.Board(5, 5);
        
        sideToPlay = abstractions.side.API.FIRST_SIDE;
        
        board.getCell(4, 2).setPiece(new Man(sideToPlay));
        board.getCell(4, 4).setPiece(new Man(sideToPlay));
        board.getCell(3, 3).setPiece(new Man(sideToPlay.getNextSide()));

        System.out.println(board);
        
        availableMutations = board.getLegalMutations(sideToPlay);
        
        System.out.println(board);
        
        System.out.println("--------------------------------------------------------------------");
        
        
        boardFactory = new BoardFactory(CheckersCell.class);
        board = boardFactory.Board(5, 5);
        
        sideToPlay = abstractions.side.API.FIRST_SIDE;
        
        board.getCell(3, 1).setPiece(new Man(sideToPlay));
        board.getCell(4, 2).setPiece(new Man(sideToPlay));
        board.getCell(4, 4).setPiece(new Man(sideToPlay));
        board.getCell(3, 3).setPiece(new Man(sideToPlay.getNextSide()));

        System.out.println(board);
        
        availableMutations = board.getLegalMutations(sideToPlay);
        
        System.out.println(board);
        
        System.out.println("--------------------------------------------------------------------");                                
        
        
    }


}