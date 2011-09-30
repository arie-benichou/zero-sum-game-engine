
package fr.designpattern.zerosumgames.abstractions.immutable.move;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.google.common.collect.Maps;

import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.position.Position;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.BoardMutation;
import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.BoardMutationInterface;

public final class Move implements MoveInterface {

    /*-------------------------------------8<-------------------------------------*/

    public static int instances;

    /*-------------------------------------8<-------------------------------------*/

    private final static class ConcreteNullMoveType implements MoveInterface {

        private ConcreteNullMoveType() {}

        @Override
        public MoveInterface apply() {
            return this;
        }

        @Override
        public PositionInterface position() {
            return Position.NULL;
        }

        @Override
        public MoveInterface apply(final PositionInterface position) {
            return this;
        }

        @Override
        public BoardMutationInterface boardMutation(/*final SideInterface side, final BoardInterface board*/) {
            return BoardMutation.NULL;
        }

        @Override
        public int compareTo(final MoveInterface that) {
            return 0;
        }

        @Override
        public ContextInterface context() {
            return null; // TODO Null Object
        }

        @Override
        public boolean isNull() {
            return true;
        }

        @Override
        public MoveInterface value() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public MoveInterface apply(final MoveInterface value) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public MoveInterface apply(final Class<? extends MoveInterface> valueClass) {
            // TODO Auto-generated method stub
            return null;
        }

    }

    /*-------------------------------------8<-------------------------------------*/

    private final static class NullMoveType implements MoveInterface {

        private final static MoveInterface INSTANCE = new NullMoveType();
        private final static MoveInterface VALUE = new ConcreteNullMoveType();

        private NullMoveType() {}

        @Override
        public MoveInterface apply() {
            return INSTANCE;
        }

        @Override
        public MoveInterface value() {
            return VALUE;
        }

        @Override
        public MoveInterface apply(final Class<? extends MoveInterface> valueClass) {
            return this.value().getClass().equals(valueClass) ? this.apply() : Factory.get(valueClass);
        }

        @Override
        public MoveInterface apply(final MoveInterface value) {
            return this.value().equals(value) ? this.apply() : Factory.get(value);
        }

        @Override
        public boolean equals(final Object object) {
            if (object == this) return true;
            if (object == null) return false;
            if (!(object instanceof MoveInterface)) return false;
            final MoveInterface that = (MoveInterface) object;
            //if (that.hashCode() != this.hashCode()) return false;
            return that.value().equals(this.value());
        }

        @Override
        public int compareTo(final MoveInterface o) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public boolean isNull() {
            return true;
        }

        @Override
        public ContextInterface context() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public PositionInterface position() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public MoveInterface apply(final PositionInterface position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public BoardMutationInterface boardMutation() {
            // TODO Auto-generated method stub
            return null;
        }

    }

    /*-------------------------------------8<-------------------------------------*/

    public final static MoveInterface NULL = new NullMoveType();

    /*-------------------------------------8<-------------------------------------*/

    private static MoveInterface newType(final Class<? extends MoveInterface> valueClass) {

        MoveInterface instance = null;

        try {
            instance = (MoveInterface) valueClass.getMethod("from").invoke(null, (Object[]) null);
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

    private final static int computeHashCode(final Class<? extends MoveInterface> valueClass) {
        return valueClass.getCanonicalName().hashCode();
    }

    private final static int computeHashCode(final MoveInterface value) {
        return (value.getClass().getCanonicalName() + "|" + value.hashCode()).hashCode();
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static class Factory {

        private static int cacheHits;

        private final static Map<Integer, MoveInterface> CACHE = Maps.newHashMap();

        public static MoveInterface get(final Class<? extends MoveInterface> valueClass) {
            if (valueClass == null) return NULL;
            /*
            final int address = computeHashCode(valueClass);
            MoveTypeInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new MoveType(newType(valueClass));
                CACHE.put(address, instance);
            }
            else
                ++cacheHits;
            return instance;
            */
            ++instances;
            return new Move(newType(valueClass));
        }

        public static MoveInterface get(MoveInterface value) {
            if (value == null) value = NULL.value();
            if (value.equals(NULL.value())) return NULL;
            /*
            final int address = computeHashCode(value);
            MoveTypeInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new MoveType(value);
                CACHE.put(address, instance);
            }
            else
                ++cacheHits;
            return instance;
            */
            ++instances;
            return new Move(value);
        }

        public final static int size() {
            return CACHE.size();
        }

        public final static int cacheHits() {
            return cacheHits;
        }

    }

    /*-------------------------------------8<-------------------------------------*/

    private final MoveInterface value;

    @Override
    public MoveInterface value() {
        return this.value;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int hashCode;

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    /*-------------------------------------8<-------------------------------------*/

    public static MoveInterface from(final MoveInterface value) {
        return NULL.apply(value);
    }

    public static MoveInterface from(final Class<? extends MoveInterface> valueClass) {
        return NULL.apply(valueClass);
    }

    private Move(final MoveInterface value) {
        ++instances;
        this.value = value;
        this.hashCode = computeHashCode(value.getClass());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public MoveInterface apply() {
        return this;
    }

    @Override
    public MoveInterface apply(final Class<? extends MoveInterface> valueClass) {
        return this.value().getClass().equals(valueClass) ? this.apply() : Factory.get(valueClass);
    }

    @Override
    public MoveInterface apply(final MoveInterface value) {
        return this.value().equals(value) ? this.apply() : Factory.get(value);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean equals(final Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (!(object instanceof MoveInterface)) return false;
        final MoveInterface that = (MoveInterface) object;
        //if (that.hashCode() != this.hashCode()) return false;
        return that.value().equals(this.value());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.value() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public int compareTo(final MoveInterface that) {
        return this.value().compareTo(that.value());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean isNull() {
        return this.value().isNull();
    }

    @Override
    public ContextInterface context() {
        return this.value.context();
    }

    @Override
    public PositionInterface position() {
        return this.value.position();
    }

    @Override
    public MoveInterface apply(final PositionInterface position) {
        return this.value.apply(position);
    }

    @Override
    public BoardMutationInterface boardMutation() {
        return this.value().boardMutation();
    }

}