
package concretisations.othello.pieces;

import abstractions.cell.old.CellInterface;
import abstractions.position.relative.RelativePositionInterface;
import abstractions.side.SideInterface;

public interface OthelloPieceInterface {

    boolean willItBeConnected(CellInterface cell, SideInterface side, RelativePositionInterface relativePosition);

}
