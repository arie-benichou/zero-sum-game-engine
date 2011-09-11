
package abstractions.immutable.move.type;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import abstractions.immutable.ImmutableInterface;
import abstractions.immutable.context.board.cell.piece.type._Pawn;

import com.google.common.collect.Maps;

public final class MoveType implements MoveTypeInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final static class NullMove implements ImmutableInterface<NullMove> {

        private final static NullMove INSTANCE = new NullMove();

        public static NullMove from() {
            return INSTANCE;
        }

        private NullMove() {}

        @Override
        public NullMove apply() {
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

    public final static MoveTypeInterface NULL = new MoveType(newType(NullMove.class));

    /*-------------------------------------8<-------------------------------------*/

    private final static int computeHashCode(final Class<?> typeClass) {
        return typeClass.getCanonicalName().hashCode();
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static class Factory {

        private static int cacheHits;

        private final static Map<Integer, MoveTypeInterface> CACHE = Maps.newHashMap();

        public static MoveTypeInterface get(Class<?> typeClass) {
            if (typeClass == null) typeClass = NullMove.class;
            if (typeClass.equals(NullMove.class)) return NULL;
            final int address = computeHashCode(typeClass);
            MoveTypeInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new MoveType(newType(typeClass));
                CACHE.put(address, instance);
            }
            else
                ++cacheHits;
            return instance;
        }

        public static MoveTypeInterface get(ImmutableInterface<?> type) {
            if (type == null) type = NULL;
            if (type.equals(NULL)) return NULL;
            final int address = type.getClass().hashCode();
            MoveTypeInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new MoveType(type);
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

    public static MoveTypeInterface from(final ImmutableInterface<?> type) {
        return NULL.apply(type);
    }

    public static MoveTypeInterface from(final Class<? extends ImmutableInterface<?>> typeClass) {
        return NULL.apply(typeClass);
    }

    private MoveType(final ImmutableInterface<?> type) {
        this.type = type;
        this.hashCode = computeHashCode(type.getClass());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public MoveTypeInterface apply() {
        return this;
    }

    @Override
    public MoveTypeInterface apply(final Class<? extends ImmutableInterface<?>> typeClass) {
        return this.type().getClass().equals(typeClass) ? this.apply() : Factory.get(typeClass);
    }

    @Override
    public MoveTypeInterface apply(final ImmutableInterface<?> type) {
        return this.type().equals(type) ? this.apply() : Factory.get(type);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean equals(final Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (!(object instanceof MoveTypeInterface)) return false;
        final MoveTypeInterface that = (MoveTypeInterface) object;
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

        final MoveTypeInterface pt1 = MoveType.from(NullMove.class);
        final MoveTypeInterface pt2 = MoveType.from(NullMove.from());

        final ImmutableInterface<Object> type = null;
        final MoveTypeInterface pt3 = MoveType.from(type);

        final Class<ImmutableInterface<Object>> typeClass = null;
        final MoveTypeInterface pt4 = MoveType.from(typeClass);

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