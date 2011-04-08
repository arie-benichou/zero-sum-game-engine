
package abstractions.piece;

import abstractions.side.SideInterface;

public interface PieceManagerInterface {

    PieceInterface getPiece(SideInterface side, PieceTypeInterface type);

}
