
package abstractions.immutable.move.type;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import abstractions.immutable.ImmutableInterface;
import abstractions.immutable.context.board.cell.piece.type._Pawn;

import com.google.common.collect.Maps;

public final class MoveType implements MoveTypeInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final static class NullMoveType implements ImmutableInterface<NullMoveType> {

        private final static NullMoveType INSTANCE = new NullMoveType();

        public static NullMoveType from() {
            return INSTANCE;
        }

        private NullMoveType() {}

        @Override
        public NullMoveType apply() {
            return this;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName();
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

    public final static MoveTypeInterface NULL = new MoveType(newType(NullMoveType.class));

    /*-------------------------------------8<-------------------------------------*/

    private final static int computeHashCode(final Class<?> valueClass) {
        return valueClass.getCanonicalName().hashCode();
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static class Factory {

        private static int cacheHits;

        private final static Map<Integer, MoveTypeInterface> CACHE = Maps.newHashMap();

        public static MoveTypeInterface get(Class<?> valueClass) {
            if (valueClass == null) valueClass = NullMoveType.class;
            if (valueClass.equals(NullMoveType.class)) return NULL;
            final int address = computeHashCode(valueClass);
            MoveTypeInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new MoveType(newType(valueClass));
                CACHE.put(address, instance);
            }
            else
                ++cacheHits;
            return instance;
        }

        public static MoveTypeInterface get(ImmutableInterface<?> value) {

            if (value == null) value = NULL;
            if (value.equals(NULL)) return NULL;

            /*
            final int address = value.getClass().hashCode();
            MoveTypeInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new MoveType(value);
                CACHE.put(address, instance);
            }
            else
                ++cacheHits;
            return instance;
            */

            return new MoveType(value);

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

    public static MoveTypeInterface from(final ImmutableInterface<?> value) {
        return NULL.apply(value);
    }

    public static MoveTypeInterface from(final Class<? extends ImmutableInterface<?>> valueClass) {
        return NULL.apply(valueClass);
    }

    private MoveType(final ImmutableInterface<?> value) {
        this.value = value;
        this.hashCode = computeHashCode(value.getClass());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public MoveTypeInterface apply() {
        return this;
    }

    @Override
    public MoveTypeInterface apply(final Class<? extends ImmutableInterface<?>> valueClass) {
        return this.value().getClass().equals(valueClass) ? this.apply() : Factory.get(valueClass);
    }

    @Override
    public MoveTypeInterface apply(final ImmutableInterface<?> value) {
        return this.value().equals(value) ? this.apply() : Factory.get(value);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean equals(final Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (!(object instanceof MoveTypeInterface)) return false;
        final MoveTypeInterface that = (MoveTypeInterface) object;
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

        final MoveTypeInterface pt1 = MoveType.from(NullMoveType.class);
        final MoveTypeInterface pt2 = MoveType.from(NullMoveType.from());

        final ImmutableInterface<Object> value = null;
        final MoveTypeInterface pt3 = MoveType.from(value);

        final Class<ImmutableInterface<Object>> valueClass = null;
        final MoveTypeInterface pt4 = MoveType.from(valueClass);

        final MoveTypeInterface pt5 = MoveType.from(_Pawn.class);
        final MoveTypeInterface pt6 = MoveType.from(_Pawn.from());

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