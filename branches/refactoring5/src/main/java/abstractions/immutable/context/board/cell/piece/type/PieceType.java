
package abstractions.immutable.context.board.cell.piece.type;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import abstractions.immutable.ImmutableInterface;

import com.google.common.collect.Maps;

public final class PieceType implements PieceTypeInterface { // TODO créer une TypeInterface de plus haut niveau

    /*-------------------------------------8<-------------------------------------*/

    private final static class NullPiece implements ImmutableInterface<NullPiece> {

        private final static NullPiece INSTANCE = new NullPiece();

        public static NullPiece from() {
            return INSTANCE;
        }

        private NullPiece() {}

        @Override
        public NullPiece apply() {
            return this;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }

    }

    /*-------------------------------------8<-------------------------------------*/

    private static ImmutableInterface<?> newType(final Class<?> typeClass) {

        ImmutableInterface<?> instance = null;

        try {
            instance = (ImmutableInterface<?>) typeClass.getMethod("from").invoke(null, (Object[]) null);
        }
        catch (final SecurityException e) {
            e.printStackTrace();
        }
        catch (final NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (final IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (final IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (final InvocationTargetException e) {
            e.printStackTrace();
        }

        return instance;
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static PieceTypeInterface NULL = new PieceType(newType(NullPiece.class));

    /*-------------------------------------8<-------------------------------------*/

    private final static int computeHashCode(final Class<?> typeClass) {
        return typeClass.getCanonicalName().hashCode();
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static class Factory {

        private static int cacheHits;

        private final static Map<Integer, PieceTypeInterface> CACHE = Maps.newHashMap();

        public static PieceTypeInterface get(Class<?> typeClass) {
            if (typeClass == null) typeClass = NullPiece.class;
            if (typeClass.equals(NullPiece.class)) return NULL;
            final int address = computeHashCode(typeClass);
            PieceTypeInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new PieceType(newType(typeClass));
                CACHE.put(address, instance);
            }
            else
                ++cacheHits;
            return instance;
        }

        public static PieceTypeInterface get(ImmutableInterface<?> type) {
            if (type == null) type = NULL;
            if (type.equals(NULL)) return NULL;
            final int address = type.getClass().hashCode();
            PieceTypeInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new PieceType(type);
                CACHE.put(address, instance);
            }
            else
                ++cacheHits;
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

    private final ImmutableInterface<?> type;

    @Override
    public ImmutableInterface<?> type() {
        return this.type;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int hashCode;

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    /*-------------------------------------8<-------------------------------------*/

    public static PieceTypeInterface from(final ImmutableInterface<?> type) {
        return NULL.apply(type);
    }

    public static PieceTypeInterface from(final Class<? extends ImmutableInterface<?>> typeClass) {
        return NULL.apply(typeClass);
    }

    private PieceType(final ImmutableInterface<?> type) {
        this.type = type;
        this.hashCode = computeHashCode(type.getClass());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public PieceTypeInterface apply() {
        return this;
    }

    @Override
    public PieceTypeInterface apply(final Class<? extends ImmutableInterface<?>> typeClass) {
        return this.type().getClass().equals(typeClass) ? this.apply() : Factory.get(typeClass);
    }

    @Override
    public PieceTypeInterface apply(final ImmutableInterface<?> type) {
        return this.type().equals(type) ? this.apply() : Factory.get(type);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean equals(final Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (!(object instanceof PieceTypeInterface)) return false;
        final PieceTypeInterface that = (PieceTypeInterface) object;
        if (that.hashCode() != this.hashCode()) return false;
        return that.type() == this.type();
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.type() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

    public static void main(final String[] args) {

        final PieceTypeInterface pt1 = PieceType.from(NullPiece.class);
        final PieceTypeInterface pt2 = PieceType.from(NullPiece.from());

        final ImmutableInterface<Object> type = null;
        final PieceTypeInterface pt3 = PieceType.from(type);

        final Class<ImmutableInterface<Object>> typeClass = null;
        final PieceTypeInterface pt4 = PieceType.from(typeClass);

        final PieceTypeInterface pt5 = PieceType.from(_Pawn.class);
        final PieceTypeInterface pt6 = PieceType.from(_Pawn.from());

        System.out.println(pt1);
        System.out.println(pt2);
        System.out.println(pt3);
        System.out.println(pt4);
        System.out.println(pt5);
        System.out.println(pt6);

        System.out.println(Factory.CACHE.size());

    }
    /*-------------------------------------8<-------------------------------------*/

}