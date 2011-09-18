
package fr.designpattern.zerosumgames.abstractions.immutable.move.type;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.position.Position;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.ConcreteMoveTypeInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.BoardMutation;
import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.BoardMutationInterface;

import com.google.common.collect.Maps;

public final class MoveType implements MoveTypeInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final static class ConcreteNullMoveType implements ConcreteMoveTypeInterface {

        private ConcreteNullMoveType() {}

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
        private final static ConcreteMoveTypeInterface VALUE = new ConcreteNullMoveType();

        private NullMoveType() {}

        @Override
        public MoveTypeInterface apply() {
            return INSTANCE;
        }

        @Override
        public ConcreteMoveTypeInterface value() {
            return VALUE;
        }

        @Override
        public MoveTypeInterface apply(final Class<? extends ConcreteMoveTypeInterface> valueClass) {
            return this.value().getClass().equals(valueClass) ? this.apply() : Factory.get(valueClass);
        }

        @Override
        public MoveTypeInterface apply(final ConcreteMoveTypeInterface value) {
            return this.value().equals(value) ? this.apply() : Factory.get(value);
        }

        @Override
        public boolean equals(final Object object) {
            if (object == this) return true;
            if (object == null) return false;
            if (!(object instanceof MoveTypeInterface)) return false;
            final MoveTypeInterface that = (MoveTypeInterface) object;
            //if (that.hashCode() != this.hashCode()) return false;
            return that.value().equals(this.value());
        }

    }

    /*-------------------------------------8<-------------------------------------*/

    public final static MoveTypeInterface NULL = new NullMoveType();

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

    private final static int computeHashCode(final Class<? extends ConcreteMoveTypeInterface> valueClass) {
        return valueClass.getCanonicalName().hashCode();
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static class Factory {

        private static int cacheHits;

        private final static Map<Integer, MoveTypeInterface> CACHE = Maps.newHashMap();

        public static MoveTypeInterface get(final Class<? extends ConcreteMoveTypeInterface> valueClass) {
            if (valueClass == null) return NULL;
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

        public static MoveTypeInterface get(ConcreteMoveTypeInterface value) {
            if (value == null) value = NULL.value();
            if (value.equals(NULL.value())) return NULL;
            final int address = value.hashCode();
            MoveTypeInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new MoveType(value);
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

}