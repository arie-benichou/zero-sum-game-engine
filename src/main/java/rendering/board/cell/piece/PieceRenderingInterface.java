
package rendering.board.cell.piece;

import rendering.RenderingInterface;
import abstractions.immutable.context.board.cell.piece.PieceInterface;

public interface PieceRenderingInterface<OUTPUT> extends RenderingInterface<PieceInterface, OUTPUT> {

    @Override
    public OUTPUT render(PieceInterface piece);

}