
package fr.designpattern.zerosumgames.abstractions.move.mutation;

import java.util.Map;

import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.piece.PieceInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.position.PositionInterface;

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