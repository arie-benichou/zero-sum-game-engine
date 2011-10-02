
package context.entity.game.board.cell.piece;

import java.util.Map;

import com.google.common.collect.Maps;

import context.entity.game.board.cell.piece.side.Side;
import context.entity.game.board.cell.piece.side.SideInterface;
import context.entity.game.board.cell.piece.type.PieceType;
import context.entity.game.board.cell.piece.type.PieceTypeInterface;

public final class Piece implements PieceInterface {

    /*-------------------------------------8<-------------------------------------*/

    public final static PieceInterface NULL = new Piece(Side.NULL, PieceType.NULL);

    /*-------------------------------------8<-------------------------------------*/

    private final static int computeHashCode(final SideInterface side, final PieceTypeInterface type) {
        return (17 * 31 + side.hashCode()) * 31 + type.hashCode();
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static class Factory {

        private static int cacheHits;

        private final static Map<Integer, PieceInterface> CACHE = Maps.newHashMap();

        public static PieceInterface get(SideInterface side, PieceTypeInterface type) {
            if (side == null) side = Side.NULL;
            if (type == null) type = PieceType.NULL;
            if (side.equals(Side.NULL) && type.equals(PieceType.NULL)) return NULL;
            final int address = computeHashCode(side, type);
            PieceInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new Piece(side, type);
                CACHE.put(address, instance);
            }
            else ++cacheHits;
            return instance;
        }

        public final static int size() {
            return CACHE.size();
        }

        public final static int cacheHits() {
            return cacheHits;
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
        return NULL.apply(side, type);
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
    public PieceInterface apply(final SideInterface side) {
        return side == null || this.side().equals(side) ? this.apply() : Factory.get(side, this.type());
    }

    @Override
    public PieceInterface apply(final PieceTypeInterface type) {
        return type == null || this.type().equals(type) ? this.apply() : Factory.get(this.side(), type);
    }

    @Override
    public PieceInterface apply(final SideInterface side, final PieceTypeInterface type) {
        return side == null && type == null || this.side().equals(side) && this.type().equals(type) ? this.apply() : Factory.get(side, type);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean equals(final Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (!(object instanceof PieceInterface)) return false;
        final PieceInterface that = (PieceInterface) object;
        //if (that.hashCode() != this.hashCode()) return false;
        return that.side().equals(this.side()) && that.type().equals(this.type());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean isNull() {
        return this.type().isNull();
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.side() + ", " + this.type() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

}
