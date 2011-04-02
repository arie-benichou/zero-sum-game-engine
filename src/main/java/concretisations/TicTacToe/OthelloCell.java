
package concretisations.TicTacToe;

import abstractions.cell.API.CellInterface;
import abstractions.cell.AbstractCell;
import abstractions.position.API.PositionInterface;
import abstractions.side.API.SideInterface;

public class OthelloCell extends AbstractCell {
    
    
    private boolean isMutable = false;

    public OthelloCell(PositionInterface position) {
        super(position);
    }

    public boolean willItBeConnected(SideInterface side, int y, int x) {

        CellInterface nextCell = this.getNext(y, x);

        //System.out.println("nextNextcell[" + nextCell.getRow() + "][" + nextCell.getColumn() + "]");

        if (nextCell.getPiece().getSide().isNull()) {
            return false;
        }

        if (nextCell.getPiece().getSide().equals(side)) {
            return true;
        }

        OthelloCell nextNextCell = (OthelloCell) nextCell.getNext(y, x);
        return nextNextCell.willItBeConnected(side, y, x);
    }
    
    public boolean checkNeighbourhood(SideInterface side) {
        boolean result = false;
        for(int y=-1; y<2; ++y) {
            for(int x=-1; x<2; ++x) {
                if(!(x==0 && y==0)) {
                    //System.out.println("(" + y + "," + x  + ")");
                    CellInterface nextCell = this.getNext(y, x);
                    if (!nextCell.getPiece().getSide().getNextSide().equals(side)) {
                        continue;
                    }
                    result = ((OthelloCell) nextCell).willItBeConnected(side, y, x);
                    if(result) {
                        break;
                    }
                    
                    
                }
            }
        }
        return result;
    }

    @Override
    public boolean isMutable(SideInterface side) {

        if (!this.isEmpty()) {
            this.isMutable = false;
            return false;
        }
        
        this.isMutable = this.checkNeighbourhood(side);
        
        return this.isMutable;
    }
    
    
    
    public static void main(String[] args) {
        
        for(int y=-1; y<2; ++y) {
            for(int x=-1; x<2; ++x) {
                if(!(x==0 && y==0)) {
                    System.out.println("(" + y + "," + x  + ")");   
                }
            }
        }
        
        
    }
    
    @Override
    public final String toString() {
        if(this.isMutable) {
            return " " + "." + " |";    
        }
        return super.toString();
    }

}
