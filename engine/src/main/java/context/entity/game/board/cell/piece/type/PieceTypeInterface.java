
package context.entity.game.board.cell.piece.type;

import util.interfaces.ImmutableInterface;

public interface PieceTypeInterface extends ImmutableInterface<PieceTypeInterface> {

    /*-------------------------------------8<-------------------------------------*/

    ConcretePieceTypeInterface value();

    PieceTypeInterface apply(Class<? extends ConcretePieceTypeInterface> typeClass);

    /*-------------------------------------8<-------------------------------------*/

}
