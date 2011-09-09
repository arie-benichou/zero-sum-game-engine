
package abstractions.immutable.pieces;

import java.util.Map;

import abstractions.immutable.pieces.types.Pawn;
import abstractions.immutable.pieces.types.PieceType;
import abstractions.immutable.pieces.types.PieceTypeInterface;
import abstractions.immutable.side.Side;
import abstractions.immutable.side.SideInterface;

import com.google.common.collect.Maps;

public final class Piece implements PieceInterface {

    /*-------------------------------------8<-------------------------------------*/

    public final static PieceInterface NULL = new Piece(Side.NULL, PieceType.NULL);

    /*-------------------------------------8<-------------------------------------*/

    private final static int computeHashCode(final SideInterface side, final PieceTypeInterface type) {
        return (17 * 31 + side.hashCode()) * 31 + type.hashCode();
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static class PieceFactory {

        private final static Map<Integer, PieceInterface> CACHE = Maps.newHashMap();

        public static PieceInterface get(final SideInterface side, final PieceTypeInterface type) {

            /*
            // TODO ? isNull
            if (side.equals(Side.NULL) && type.equals(PieceType.NULL)) {
                return NULL;
            }
            */

            final int address = computeHashCode(side, type);
            PieceInterface instance = CACHE.get(address);

            if (instance == null) {
                instance = new Piece(side, type);
                CACHE.put(address, instance);
            }
            return instance;
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
        return PieceFactory.get(side == null ? Side.NULL : side, type == null ? PieceType.NULL : type);
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
        return this.getClass().getSimpleName() + "(" + this.side() + ", " + this.type() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

    public static void main(final String[] args) {

        /*
        System.out.println(Piece.from(null, null));
        System.out.println(Piece.from(Side.from(0), null));
        System.out.println(Piece.from(null, PieceType.NULL));
        System.out.println(Piece.from(Side.from(0), PieceType.NULL));
        System.out.println(Piece.from(Side.from(1), PieceType.NULL));
        System.out.println(Piece.from(Side.from(0), PieceType.from(Pawn.class)));
        System.out.println(Piece.from(Side.from(1), PieceType.from(Pawn.class)));
        System.out.println(Piece.from(Side.from(1).opposite(), PieceType.from(Pawn.class)).apply(Side.from(2).opposite()));
        */

        final long t0 = System.currentTimeMillis();

        for (int n = 0; n < 2; ++n) {

            Piece.from(null, null);
            Piece.from(Side.from(0), null);
            Piece.from(null, PieceType.NULL);
            Piece.from(Side.from(0), PieceType.NULL);
            Piece.from(Side.from(1), PieceType.NULL);
            Piece.from(Side.from(0), PieceType.from(Pawn.class)); // TODO !!!!!! factory
            /*
            Piece.from(Side.from(1), PieceType.from(Pawn.class));
            Piece.from(Side.from(1).opposite(), PieceType.from(Pawn.class)).apply(Side.from(2).opposite());
            Piece.from(Side.from(1).opposite(), PieceType.from(Pawn.class)).apply(PieceType.NULL);
            Piece.from(Side.from(1).opposite(), PieceType.from(Pawn.class)).apply(PieceType.from(Pawn.class));
            */

        }

        final long t1 = System.currentTimeMillis();

        System.out.println(t1 - t0 + " ms");

        System.out.println(PieceFactory.CACHE.size());

    }
}
