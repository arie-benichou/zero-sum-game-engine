
package rendering.board.cell;

import java.util.Map;

import rendering.board.cell.piece.PieceStringRendering;
import abstractions.immutable.context.board.cell.BoardCellInterface;

public class BoardCellStringRendering implements BoardCellRenderingInterface<String> {

    private final PieceStringRendering pieceStringRendering;

    public BoardCellStringRendering(final PieceStringRendering pieceStringRendering) {
        this.pieceStringRendering = pieceStringRendering;
    }

    @Override
    public String render(final BoardCellInterface cell) {
        return this.render(cell, null);
    }

    public String render(final BoardCellInterface cell, final Map<Object, Object> symbols) {

        if (symbols != null) {
            final Object value = symbols.get(cell);
            if (value != null)
                return value.toString();
        }

        return this.pieceStringRendering.render(cell.value(), symbols);
    }
}