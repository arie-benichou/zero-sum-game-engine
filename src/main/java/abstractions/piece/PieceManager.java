
package abstractions.piece;

import java.util.Map;
import java.util.Set;

import abstractions.side.SideInterface;

import com.google.common.collect.Maps;

public class PieceManager implements PieceManagerInterface {

    private final PieceSetFactoryInterface factory = new PieceSetFactory();
    private final Map<Integer, PieceInterface> data;

    private final int hash(final SideInterface side, final PieceTypeInterface type) {
        return side.hashCode() + type.hashCode();
    }

    public <T extends Enum<T> & PieceTypeInterface> PieceManager(final Class<T> pieceTypeSetClass) {
        final Set<PieceInterface> set = this.factory.newPieceSet(pieceTypeSetClass);
        this.data = Maps.newHashMapWithExpectedSize(set.size());
        for (final PieceInterface element : set) {
            this.data.put(this.hash(element.getSide(), element.getType()), element);
        }
        if (this.data.size() != set.size()) {
            throw new RuntimeException("Method hash is not valid for this set of pieces");
        }
    }

    @Override
    public PieceInterface getPiece(final SideInterface side, final PieceTypeInterface type) {
        final PieceInterface piece = this.data.get(this.hash(side, type));
        if (piece == null) {
            throw new IllegalPieceException(side, type);
        }
        return piece;
    }

}
