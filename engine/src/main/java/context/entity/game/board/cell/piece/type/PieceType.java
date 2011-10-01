
package context.entity.game.board.cell.piece.type;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.google.common.collect.Maps;

import context.entity.game.board.BoardInterface;
import context.entity.game.board.cell.piece.side.SideInterface;
import context.entity.game.board.cell.position.PositionInterface;

public final class PieceType implements PieceTypeInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final static class NullConcretePieceType implements ConcretePieceTypeInterface {

        private final static ConcretePieceTypeInterface INSTANCE = new NullConcretePieceType();

        public static ConcretePieceTypeInterface from() {
            return INSTANCE;
        }

        private NullConcretePieceType() {}

        @Override
        public ConcretePieceTypeInterface apply() {
            return this;
        }

        @Override
        public boolean hasApplication(final SideInterface side, final BoardInterface board, final PositionInterface position) {
            return false;
        }

        @Override
        public String toString() {
            return this.getClass().getCanonicalName();
        }

    }

    /*-------------------------------------8<-------------------------------------*/

    private final static int computeHashCode(final Class<? extends ConcretePieceTypeInterface> valueClass) {
        return valueClass.getCanonicalName().hashCode();
    }

    /*-------------------------------------8<-------------------------------------*/

    private final static class NullPieceType implements PieceTypeInterface {

        /*-------------------------------------8<-------------------------------------*/

        private final static PieceTypeInterface INSTANCE = new NullPieceType();

        /*-------------------------------------8<-------------------------------------*/

        private final ConcretePieceTypeInterface value = NullConcretePieceType.from();
        private final int hashCode = computeHashCode(this.value.getClass());

        /*-------------------------------------8<-------------------------------------*/

        public static PieceTypeInterface from() {
            return INSTANCE;
        }

        private NullPieceType() {}

        /*-------------------------------------8<-------------------------------------*/

        @Override
        public int hashCode() {
            return this.hashCode;
        }

        @Override
        public ConcretePieceTypeInterface value() {
            return this.value;
        }

        /*-------------------------------------8<-------------------------------------*/

        @Override
        public PieceTypeInterface apply() {
            return this;
        }

        @Override
        public PieceTypeInterface apply(final Class<? extends ConcretePieceTypeInterface> valueClass) {
            return Factory.get(valueClass);
        }

        /*-------------------------------------8<-------------------------------------*/

        @Override
        public String toString() {
            return this.getClass().getSimpleName() + "(" + this.value() + ")";
        }

        /*-------------------------------------8<-------------------------------------*/

    }

    /*-------------------------------------8<-------------------------------------*/

    public final static PieceTypeInterface NULL = NullPieceType.from();

    /*-------------------------------------8<-------------------------------------*/

    private static ConcretePieceTypeInterface newType(final Class<? extends ConcretePieceTypeInterface> valueClass) {

        ConcretePieceTypeInterface instance = null;

        try {
            instance = (ConcretePieceTypeInterface) valueClass.getMethod("from").invoke(null, (Object[]) null);
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

    public final static class Factory {

        private static int cacheHits;

        private final static Map<Integer, PieceTypeInterface> CACHE = Maps.newHashMap();

        public static PieceTypeInterface get(Class<? extends ConcretePieceTypeInterface> valueClass) {

            if (valueClass == null) valueClass = NullConcretePieceType.class;
            if (valueClass.equals(NullConcretePieceType.class)) return NULL;

            final int address = computeHashCode(valueClass);
            PieceTypeInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new PieceType(newType(valueClass));
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

    public static PieceTypeInterface from(final Class<? extends ConcretePieceTypeInterface> valueClass) {
        return NULL.apply(valueClass);
    }

    public static PieceTypeInterface from(final ConcretePieceTypeInterface value) {
        return from(value.getClass());
    }

    /*-------------------------------------8<-------------------------------------*/

    private final ConcretePieceTypeInterface value;

    @Override
    public ConcretePieceTypeInterface value() {
        return this.value;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int hashCode;

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    /*-------------------------------------8<-------------------------------------*/

    private PieceType(final ConcretePieceTypeInterface concretePieceType) {
        this.value = concretePieceType;
        this.hashCode = computeHashCode(concretePieceType.getClass());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public PieceTypeInterface apply() {
        return this;
    }

    @Override
    public PieceTypeInterface apply(final Class<? extends ConcretePieceTypeInterface> valueClass) {
        return this.value().getClass().equals(valueClass) ? this.apply() : Factory.get(valueClass);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.value() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

}