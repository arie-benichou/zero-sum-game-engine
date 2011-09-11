
package rendering.board.cell;

import rendering.board.cell.piece.PieceStringRendering;
import abstractions.immutable.context.board.cell.BoardCellInterface;

public class BoardCellStringRendering implements BoardCellRenderingInterface<String> {

    private final PieceStringRendering pieceStringRendering;

    public BoardCellStringRendering(final PieceStringRendering pieceStringRendering) {
        this.pieceStringRendering = pieceStringRendering;
    }

    @Override
    public String render(final BoardCellInterface cell) {
        return this.pieceStringRendering.render(cell.value());
    }
}