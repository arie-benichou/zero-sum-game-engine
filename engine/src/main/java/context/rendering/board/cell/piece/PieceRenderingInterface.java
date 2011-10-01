
package context.rendering.board.cell.piece;

import context.entity.game.board.cell.piece.PieceInterface;
import context.rendering.RenderingInterface;

public interface PieceRenderingInterface<OUTPUT> extends RenderingInterface<PieceInterface, OUTPUT> {

    @Override
    public OUTPUT render(PieceInterface piece);

}