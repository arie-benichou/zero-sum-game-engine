
package fr.designpattern.zerosumgames.abstractions.immutable.move.mutation;

import java.util.Collections;
import java.util.Map;

import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Ordering;

import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.PieceInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.position.PositionInterface;

public final class BoardMutation implements BoardMutationInterface {

    /*-------------------------------------8<-------------------------------------*/

    @SuppressWarnings("unchecked")
    public final static BoardMutationInterface NULL = new BoardMutation(Collections.EMPTY_MAP);

    /*-------------------------------------8<-------------------------------------*/

    public static int instances;

    /*-------------------------------------8<-------------------------------------*/

    private final ImmutableSortedMap<PositionInterface, PieceInterface> value;

    @Override
    public ImmutableSortedMap<PositionInterface, PieceInterface> value() {
        return this.value;
    }

    /*-------------------------------------8<-------------------------------------*/

    public static BoardMutationInterface from(final Map<PositionInterface, PieceInterface> value) {
        return NULL.apply(value);
    }

    private BoardMutation(final Map<PositionInterface, PieceInterface> value) {
        this.value = new ImmutableSortedMap.Builder<PositionInterface, PieceInterface>(Ordering.natural()).putAll(value).build();
        //this.value = ImmutableSortedMap.copyOf(value);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public BoardMutationInterface apply() {
        return this;
    }

    @Override
    public BoardMutationInterface apply(final Map<PositionInterface, PieceInterface> value) { // TODO un apply additif serait plus utile
        return value.equals(this.value()) ? this.apply() : new BoardMutation(value);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean equals(final Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (!(object instanceof BoardMutationInterface)) return false;
        return ((BoardMutationInterface) object).value().equals(this.value());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.value() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

}