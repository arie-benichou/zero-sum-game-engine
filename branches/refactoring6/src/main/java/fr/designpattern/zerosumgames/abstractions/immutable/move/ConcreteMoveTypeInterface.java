
package fr.designpattern.zerosumgames.abstractions.immutable.move;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.BoardMutationInterface;

public interface ConcreteMoveTypeInterface extends ImmutableInterface<ConcreteMoveTypeInterface> {

    PositionInterface position();

    ConcreteMoveTypeInterface apply(PositionInterface position);

    BoardMutationInterface computeBoardMutation(SideInterface side, BoardInterface board);

}
