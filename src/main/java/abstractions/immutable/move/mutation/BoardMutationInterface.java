
package abstractions.immutable.move.mutation;

import abstractions.immutable.context.board.cell.piece.PieceInterface;
import abstractions.immutable.context.board.cell.position.PositionInterface;

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

    BoardMutationInterface apply(ImmutableSortedMap<PositionInterface, PieceInterface> value);

}