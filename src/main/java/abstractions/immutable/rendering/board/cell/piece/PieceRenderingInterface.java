
package abstractions.immutable.rendering.board.cell.piece;

import abstractions.immutable.context.gameplay.game.board.cell.piece.PieceInterface;
import abstractions.immutable.rendering.RenderingInterface;

public interface PieceRenderingInterface<OUTPUT> extends RenderingInterface<PieceInterface, OUTPUT> {

    @Override
    public OUTPUT render(PieceInterface piece);

}