
package fr.designpattern.zerosumgames.abstractions.immutable.move;

import java.util.Map;

import com.google.common.collect.Maps;

import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.BoardMutation;
import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.BoardMutationInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveType;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public final class Move implements MoveInterface {

    public static int instances;

    /*-------------------------------------8<-------------------------------------*/

    public final static MoveInterface NULL = new Move(MoveType.NULL, BoardMutation.NULL);

    /*-------------------------------------8<-------------------------------------*/

    private final static int computeHashCode(final MoveTypeInterface type, final BoardMutationInterface boardMutation) {
        return (17 * 31 + type.hashCode()) * 31 + boardMutation.hashCode();
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static class Factory {

        private static int cacheHits;
        private final static Map<Integer, MoveInterface> CACHE = Maps.newHashMap();

        public static MoveInterface get(MoveTypeInterface type, BoardMutationInterface boardMutation) {
            if (type == null) type = MoveType.NULL;
            if (boardMutation == null) boardMutation = BoardMutation.NULL;
            if (type.equals(MoveType.NULL) && boardMutation.equals(BoardMutation.NULL)) return NULL;
            /*
            final int address = computeHashCode(type, boardMutation);
            MoveInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new Move(type, boardMutation);
                CACHE.put(address, instance);
            }
            else {
                ++cacheHits;
            }
            return instance;
            */
            ++instances;
            return new Move(type, boardMutation);
        }

        public final static int size() {
            return CACHE.size();
        }

        public final static int cacheHits() {
            return cacheHits;
        }

    }

    /*-------------------------------------8<-------------------------------------*/

    private final MoveTypeInterface type;

    @Override
    public MoveTypeInterface type() {
        return this.type;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final BoardMutationInterface mutation;

    @Override
    public BoardMutationInterface mutation() {
        return this.mutation;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int hashCode;

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    /*-------------------------------------8<-------------------------------------*/

    public static MoveInterface from(final MoveTypeInterface type, final BoardMutationInterface mutation) {
        return NULL.apply(type, mutation);
    }

    private Move(final MoveTypeInterface type, final BoardMutationInterface mutation) {
        this.type = type;
        this.mutation = mutation;
        this.hashCode = computeHashCode(type, mutation);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public MoveInterface apply() {
        return this;
    }

    @Override
    public MoveInterface apply(final MoveTypeInterface type) {
        return this.type().equals(type) ? this.apply() : Factory.get(type, this.mutation());
    }

    @Override
    public MoveInterface apply(final BoardMutationInterface mutation) {
        return this.mutation().equals(mutation) ? this.apply() : Factory.get(this.type(), mutation);
    }

    @Override
    public MoveInterface apply(final MoveTypeInterface type, final BoardMutationInterface mutation) {
        return this.type().equals(type) && this.mutation().equals(mutation) ? this.apply() : Factory.get(type, mutation);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean equals(final Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (!(object instanceof MoveInterface)) return false;
        final MoveInterface that = (MoveInterface) object;
        //if (that.hashCode() != this.hashCode()) return false;
        return that.type().equals(this.type()) && that.mutation().equals(this.mutation());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.type() + ", " + this.mutation() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

}