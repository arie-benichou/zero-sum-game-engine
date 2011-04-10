
package abstractions.mutation;

import abstractions.cell.ManagedCellInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

public interface MutationTypeInterface {

    MutationInterface operation(final ManagedCellInterface cell, final SideInterface side, final PieceTypeInterface pieceType);

}
