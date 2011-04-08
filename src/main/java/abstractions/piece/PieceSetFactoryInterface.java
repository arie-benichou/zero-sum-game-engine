
package abstractions.piece;

import java.util.Set;

public interface PieceSetFactoryInterface {

    public <T extends Enum<T> & PieceTypeInterface> Set<PieceInterface> newPieceSet(final Class<T> piecesSet);

}
