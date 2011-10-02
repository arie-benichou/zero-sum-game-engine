
package context.entity.game.board.cell.piece.type;

import util.interfaces.ImmutableInterface;
import util.interfaces.NullObjectInterface;

public interface PieceTypeInterface extends ImmutableInterface<PieceTypeInterface>, NullObjectInterface {

    /*-------------------------------------8<-------------------------------------*/

    ConcretePieceTypeInterface value();

    PieceTypeInterface apply(Class<? extends ConcretePieceTypeInterface> typeClass);

    /*-------------------------------------8<-------------------------------------*/

}
