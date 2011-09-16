
package abstractions.immutable.context.gameplay.game.board.cell.piece.type;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import abstractions.immutable.ImmutableInterface;

import com.google.common.collect.Maps;

public final class PieceType implements PieceTypeInterface { // TODO créer une TypeInterface de plus haut niveau

    /*-------------------------------------8<-------------------------------------*/

    public final static class NullPieceType implements ImmutableInterface<NullPieceType> {

        private final static NullPieceType INSTANCE = new NullPieceType();

        public static NullPieceType from() {
            return INSTANCE;
        }

        private NullPieceType() {}

        @Override
        public NullPieceType apply() {
            return this;
        }

        @Override
        public String toString() {
            return this.getClass().getCanonicalName();
        }
    }

    /*-------------------------------------8<-------------------------------------*/

    private static ImmutableInterface<?> newType(final Class<?> valueClass) {

        ImmutableInterface<?> instance = null;

        try {
            instance = (ImmutableInterface<?>) valueClass.getMethod("from").invoke(null, (Object[]) null);
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

    public final static PieceTypeInterface NULL = new PieceType(newType(NullPieceType.class));

    /*-------------------------------------8<-------------------------------------*/

    private final static int computeHashCode(final Class<?> valueClass) {
        return valueClass.getCanonicalName().hashCode();
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static class Factory {

        private static int cacheHits;

        private final static Map<Integer, PieceTypeInterface> CACHE = Maps.newHashMap();

        public static PieceTypeInterface get(Class<?> valueClass) {
            if (valueClass == null) valueClass = NullPieceType.class;
            if (valueClass.equals(NullPieceType.class)) return NULL;
            final int address = computeHashCode(valueClass);
            PieceTypeInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new PieceType(newType(valueClass));
                CACHE.put(address, instance);
            }
            else
                ++cacheHits;
            return instance;
        }

        public static PieceTypeInterface get(ImmutableInterface<?> value) {
            if (value == null) value = NULL;
            if (value.equals(NULL)) return NULL;
            final int address = value.getClass().hashCode();
            PieceTypeInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new PieceType(value);
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

    private final ImmutableInterface<?> value;

    @Override
    public ImmutableInterface<?> value() {
        return this.value;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int hashCode;

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    /*-------------------------------------8<-------------------------------------*/

    public static PieceTypeInterface from(final ImmutableInterface<?> value) {
        return NULL.apply(value);
    }

    public static PieceTypeInterface from(final Class<? extends ImmutableInterface<?>> valueClass) {
        return NULL.apply(valueClass);
    }

    private PieceType(final ImmutableInterface<?> value) {
        this.value = value;
        this.hashCode = computeHashCode(value.getClass());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public PieceTypeInterface apply() {
        return this;
    }

    @Override
    public PieceTypeInterface apply(final Class<? extends ImmutableInterface<?>> valueClass) {
        return this.value().getClass().equals(valueClass) ? this.apply() : Factory.get(valueClass);
    }

    @Override
    public PieceTypeInterface apply(final ImmutableInterface<?> value) {
        return this.value().equals(value) ? this.apply() : Factory.get(value);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean equals(final Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (!(object instanceof PieceTypeInterface)) return false;
        final PieceTypeInterface that = (PieceTypeInterface) object;
        if (that.hashCode() != this.hashCode()) return false;
        return that.value() == this.value();
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.value() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

    public static void main(final String[] args) {

        final PieceTypeInterface pt1 = PieceType.from(NullPieceType.class);
        final PieceTypeInterface pt2 = PieceType.from(NullPieceType.from());

        final ImmutableInterface<Object> value = null;
        final PieceTypeInterface pt3 = PieceType.from(value);

        final Class<ImmutableInterface<Object>> valueClass = null;
        final PieceTypeInterface pt4 = PieceType.from(valueClass);

        //final PieceTypeInterface pt5 = PieceType.from(_Pawn.class);
        //final PieceTypeInterface pt6 = PieceType.from(_Pawn.from());

        System.out.println(pt1);
        System.out.println(pt2);
        System.out.println(pt3);
        System.out.println(pt4);
        //System.out.println(pt5);
        //System.out.println(pt6);

        System.out.println(Factory.CACHE.size());

    }
    /*-------------------------------------8<-------------------------------------*/

}