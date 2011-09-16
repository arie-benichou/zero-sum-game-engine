
package abstractions.immutable.move.mutation;

import java.util.Map;

import abstractions.immutable.context.gameplay.game.board.cell.piece.PieceInterface;
import abstractions.immutable.context.gameplay.game.board.cell.position.PositionInterface;

import com.google.common.collect.ImmutableSortedMap;

public interface BoardMutationInterface extends MutationInterface<PositionInterface, PieceInterface> {

    @Override
    public ImmutableSortedMap<PositionInterface, PieceInterface> value();

    @Override
    public BoardMutationInterface apply();

    @Override
    public int hashCode();

    @Override
    public String toString();

    BoardMutationInterface apply(Map<PositionInterface, PieceInterface> value);

}