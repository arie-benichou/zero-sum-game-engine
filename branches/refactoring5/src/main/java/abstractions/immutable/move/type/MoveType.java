
package abstractions.immutable.move.type;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import abstractions.immutable.context.board.BoardInterface;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.board.cell.position.Position;
import abstractions.immutable.context.board.cell.position.PositionInterface;
import abstractions.immutable.move.ConcreteMoveTypeInterface;
import abstractions.immutable.move.mutation.BoardMutation;
import abstractions.immutable.move.mutation.BoardMutationInterface;

import com.google.common.collect.Maps;

public final class MoveType implements MoveTypeInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final static class ConcreteNullMove implements ConcreteMoveTypeInterface {

        private ConcreteNullMove() {}

        @Override
        public ConcreteMoveTypeInterface apply() {
            return this;
        }

        @Override
        public PositionInterface position() {
            return Position.NULL;
        }

        @Override
        public ConcreteMoveTypeInterface apply(final PositionInterface position) {
            return this;
        }

        @Override
        public BoardMutationInterface computeBoardMutation(final SideInterface side, final BoardInterface board) {
            return BoardMutation.NULL;
        }

    }

    /*-------------------------------------8<-------------------------------------*/

    private final static class NullMoveType implements MoveTypeInterface {

        private final static MoveTypeInterface INSTANCE = new NullMoveType();
        private final ConcreteMoveTypeInterface value = new ConcreteNullMove();

        private NullMoveType() {}

        @Override
        public MoveTypeInterface apply() {
            return INSTANCE;
        }

        @Override
        public ConcreteMoveTypeInterface value() {
            return this.value;
        }

        @Override
        public MoveTypeInterface apply(final Class<? extends ConcreteMoveTypeInterface> valueClass) {
            return this.value().getClass().equals(valueClass) ? this.apply() : Factory.get(valueClass);
        }

        @Override
        public MoveTypeInterface apply(final ConcreteMoveTypeInterface value) {
            return this.value().equals(value) ? this.apply() : Factory.get(value);
        }

    }

    /*-------------------------------------8<-------------------------------------*/

    private static ConcreteMoveTypeInterface newType(final Class<? extends ConcreteMoveTypeInterface> valueClass) {

        ConcreteMoveTypeInterface instance = null;

        try {
            instance = (ConcreteMoveTypeInterface) valueClass.getMethod("from").invoke(null, (Object[]) null);
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

    public final static MoveTypeInterface NULL = new NullMoveType();

    /*-------------------------------------8<-------------------------------------*/

    private final static int computeHashCode(final Class<? extends ConcreteMoveTypeInterface> valueClass) {
        return valueClass.getCanonicalName().hashCode();
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static class Factory {

        private static int cacheHits;

        private final static Map<Integer, MoveTypeInterface> CACHE = Maps.newHashMap();

        public static MoveTypeInterface get(final Class<? extends ConcreteMoveTypeInterface> valueClass) {
            //if (valueClass == null) valueClass = NullMoveType.class;
            //if (valueClass.equals(NullMoveType.class)) return NULL;
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

        public static MoveTypeInterface get(final ConcreteMoveTypeInterface value) {

            //if (value == null) value = NULL;
            //if (value.equals(NULL)) return NULL;

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

    private final ConcreteMoveTypeInterface value;

    @Override
    public ConcreteMoveTypeInterface value() {
        return this.value;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int hashCode;

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    /*-------------------------------------8<-------------------------------------*/

    public static MoveTypeInterface from(final ConcreteMoveTypeInterface value) {
        return NULL.apply(value);
    }

    public static MoveTypeInterface from(final Class<? extends ConcreteMoveTypeInterface> valueClass) {
        return NULL.apply(valueClass);
    }

    private MoveType(final ConcreteMoveTypeInterface value) {
        this.value = value;
        this.hashCode = computeHashCode(value.getClass());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public MoveTypeInterface apply() {
        return this;
    }

    @Override
    public MoveTypeInterface apply(final Class<? extends ConcreteMoveTypeInterface> valueClass) {
        return this.value().getClass().equals(valueClass) ? this.apply() : Factory.get(valueClass);
    }

    @Override
    public MoveTypeInterface apply(final ConcreteMoveTypeInterface value) {
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
        return that.value().equals(this.value());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.value() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

    public static void main(final String[] args) {

        //final MoveTypeInterface pt1 = MoveType.from(NullMoveType.class);
        //final MoveTypeInterface pt2 = MoveType.from(NullMoveType.from());

        /*
        final ConcreteMoveTypeInterface value = null;
        final MoveTypeInterface pt3 = MoveType.from(value);

        final Class<ConcreteMoveTypeInterface> valueClass = null;
        final MoveTypeInterface pt4 = MoveType.from(valueClass);
        */

        final MoveTypeInterface pt5 = MoveType.from(_NewPawn.class);
        final MoveTypeInterface pt6 = MoveType.from(_NewPawn.from());

        //System.out.println(pt1);
        //System.out.println(pt2);
        //System.out.println(pt3);
        //System.out.println(pt4);
        System.out.println(pt5);
        System.out.println(pt6);

        System.out.println(Factory.CACHE.size());

    }
    /*-------------------------------------8<-------------------------------------*/

}