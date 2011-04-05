
package concretisations.othello.pieces;

import abstractions.cell.CellInterface;
import abstractions.position.RelativePositionInterface;
import abstractions.side.SideInterface;

public interface OthelloPieceInterface {

    boolean willItBeConnected(CellInterface cell, SideInterface side, RelativePositionInterface relativePosition);

}
