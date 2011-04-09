
package abstractions.piece;

import java.util.Collections;
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

    private Map<Integer, PieceInterface> initializeData(final Set<PieceInterface> set) {
        final Map<Integer, PieceInterface> data = Maps.newHashMapWithExpectedSize(set.size());
        for (final PieceInterface element : set) {
            data.put(this.hash(element.getSide(), element.getType()), element);
        }
        if (data.size() != set.size()) {
            throw new RuntimeException("Method hash is not valid for this set of pieces");
        }
        return Collections.unmodifiableMap(data);
    }

    public <T extends Enum<T> & PieceTypeInterface> PieceManager(final Class<T> pieceTypeSetClass) {
        final Set<PieceInterface> set = this.factory.newPieceSet(pieceTypeSetClass);
        this.data = this.initializeData(set);
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
