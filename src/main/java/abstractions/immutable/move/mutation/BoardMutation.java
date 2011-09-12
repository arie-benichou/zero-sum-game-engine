
package abstractions.immutable.move.mutation;

import java.util.Map;
import java.util.Map.Entry;

import abstractions.immutable.context.board.cell.piece.PieceInterface;
import abstractions.immutable.context.board.cell.position.PositionInterface;

import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

public class BoardMutation implements BoardMutationInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final static ImmutableSortedMap<PositionInterface, PieceInterface> emptyMap = ImmutableSortedMap.of();

    /*-------------------------------------8<-------------------------------------*/

    public final static BoardMutationInterface NULL = new BoardMutation(emptyMap);

    /*-------------------------------------8<-------------------------------------*/

    private final static int computeHashCode(final Map<PositionInterface, PieceInterface> value) {
        int hashCode = 0;
        for (final Entry<PositionInterface, PieceInterface> entry : value.entrySet()) {
            int entryHascode = 0;
            entryHascode = 31 * entryHascode + entry.getKey().hashCode();
            entryHascode = 31 * entryHascode + entry.getValue().hashCode();
            hashCode = 31 * hashCode + entryHascode;
        }
        return hashCode;
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static class Factory {

        private static int cacheHits;

        private final static Map<Integer, BoardMutationInterface> CACHE = Maps.newHashMap();

        public static BoardMutationInterface get(final Map<PositionInterface, PieceInterface> value) {

            if (value == null || value.isEmpty()) return NULL;

            final ImmutableSortedMap<PositionInterface, PieceInterface> orderedCopy =
                    new ImmutableSortedMap.Builder<PositionInterface, PieceInterface>(Ordering.natural()).putAll(value).build();

            final int address = computeHashCode(orderedCopy);
            BoardMutationInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new BoardMutation(orderedCopy);
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

    private final ImmutableSortedMap<PositionInterface, PieceInterface> value;

    @Override
    public ImmutableSortedMap<PositionInterface, PieceInterface> value() {
        return this.value;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int hashCode;

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    /*-------------------------------------8<-------------------------------------*/

    public static BoardMutationInterface from(final Map<PositionInterface, PieceInterface> value) {
        return NULL.apply(value);
    }

    private BoardMutation(final ImmutableSortedMap<PositionInterface, PieceInterface> value) {
        this.value = value;
        this.hashCode = computeHashCode(value);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public BoardMutationInterface apply() {
        return this;
    }

    @Override
    // TODO un apply additif serait plus utile
    public BoardMutationInterface apply(final Map<PositionInterface, PieceInterface> value) {
        return value.equals(this.value()) ? this.apply() : Factory.get(value);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean equals(final Object object) { // TODO tester la méthode equals sur une map
        if (object == this)
            return true;
        if (object == null)
            return false;
        if (!(object instanceof BoardMutationInterface))
            return false;
        return ((BoardMutationInterface) object).value().equals(this.value());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.value() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

    public static void main(final String[] args) {

    }

    /*-------------------------------------8<-------------------------------------*/

}