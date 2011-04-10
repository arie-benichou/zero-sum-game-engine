
package concretisations.othello.pieces;

import abstractions.cell.old.ManagedCellInterface;
import abstractions.position.relative.RelativePositionInterface;
import abstractions.side.SideInterface;

public interface OthelloPieceInterface {

    boolean willItBeConnected(ManagedCellInterface cell, SideInterface side, RelativePositionInterface relativePosition);

}
