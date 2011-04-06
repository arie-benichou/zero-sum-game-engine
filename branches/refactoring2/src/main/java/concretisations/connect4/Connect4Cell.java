
package concretisations.connect4;

import abstractions.cell.Cell;
import abstractions.position.API.PositionInterface;
import abstractions.side.API.SideInterface;


//TODO ! à virer, mettre le comportement dans les pièces (pièce nulle comrise)
public class Connect4Cell extends Cell {

    public Connect4Cell(AbsolutePositionInterface position) {
        super(position);
    }

    @Override
    public boolean isMutable(SideInterface side) {
        return this.willGenerateMutations = this.isEmpty() && !this.getNext(1, 0).isEmpty();
    }
    
    @Override
    public final String toString() {
        return this.willGenerateMutations ? " " + "." + " |" : super.toString();
    }
    
}