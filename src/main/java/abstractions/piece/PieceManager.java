
package abstractions.piece;

import java.util.Map;
import java.util.Set;

import abstractions.side.SideInterface;

import com.google.common.collect.Maps;

public class PieceManager implements PieceManagerInterface {

    private final PieceSetFactoryInterface pieceSetFactory = new PieceSetFactory();
    private Map<Integer, PieceInterface> pieces;

    public <T extends Enum<T> & PieceTypeInterface> PieceManager(Class<T> pieceTypeSetClass) {
        Set<PieceInterface> pieceSet = this.pieceSetFactory.newPieceSet(pieceTypeSetClass);
        this.pieces = Maps.newHashMapWithExpectedSize(pieceSet.size());
        for (PieceInterface piece : pieceSet) {
            this.pieces.put(this.hash(piece.getSide(), piece.getType()), piece);
        }
    }

    private final int hash(final SideInterface side, final PieceTypeInterface type) {
        return side.hashCode() + type.hashCode();
    }

    public PieceInterface getPiece(SideInterface side, PieceTypeInterface type) {
        PieceInterface piece = this.pieces.get(this.hash(side, type));
        if (piece == null) {
            throw new IllegalPieceException(side, type);
        }
        return piece;
    }

}
