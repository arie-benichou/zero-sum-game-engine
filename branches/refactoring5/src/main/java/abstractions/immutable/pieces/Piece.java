
package abstractions.immutable.pieces;

import java.util.Map;

import abstractions.immutable.side.SideInterface;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

public final class Piece implements PieceInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final static int computeHashCode(final SideInterface side, final PieceTypeInterface type) {
        return (17 * 31 + side.hashCode()) * 31 + type.hashCode();
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static class PieceFactory {

        private final static Map<Integer, PieceInterface> CACHE = Maps.newHashMap();

        public static PieceInterface get(final SideInterface side, final PieceTypeInterface type) {
            PieceInterface piece;
            final int address = computeHashCode(side, type);
            if ((piece = CACHE.get(address)) == null) {
                piece = new Piece(side, type);
                CACHE.put(address, piece);
            }
            return piece;
        }

    }

    /*-------------------------------------8<-------------------------------------*/
    private final SideInterface side;

    @Override
    public SideInterface side() {
        return this.side;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final PieceTypeInterface type;

    @Override
    public PieceTypeInterface type() {
        return this.type;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int hashCode;

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    /*-------------------------------------8<-------------------------------------*/

    public static PieceInterface from(final SideInterface side, final PieceTypeInterface type) {
        Preconditions.checkNotNull(side, "side is not intended to be null.");
        Preconditions.checkNotNull(type, "type is not intended to be null.");
        return PieceFactory.get(side, type);
    }

    private Piece(final SideInterface side, final PieceTypeInterface type) {
        this.side = side;
        this.type = type;
        this.hashCode = computeHashCode(side, type);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public PieceInterface apply() {
        return this;
    }

    @Override
    public PieceInterface apply(final PieceTypeInterface type) {
        return type == null || type.equals(this.type()) ? this.apply() : from(this.side(), type);
    }

    @Override
    public PieceInterface apply(final SideInterface side) {
        return side == null || side.equals(this.side()) ? this.apply() : from(side, this.type());
    }

    @Override
    public PieceInterface apply(final SideInterface side, final PieceTypeInterface type) {
        return this.apply(side).apply(type);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean equals(final Object object) {
        if (object == this)
            return true;
        if (object == null)
            return false;
        if (!(object instanceof PieceInterface))
            return false;
        final PieceInterface that = (PieceInterface) object;
        if (that.hashCode() != this.hashCode())
            return false;
        return that.side() == this.side() && that.type() == this.type();
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "( " + this.side() + " , " + this.type() + " ) ";
    }

    /*-------------------------------------8<-------------------------------------*/

}
