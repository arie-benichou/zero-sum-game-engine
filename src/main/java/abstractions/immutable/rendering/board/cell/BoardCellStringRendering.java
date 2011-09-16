
package abstractions.immutable.rendering.board.cell;

import java.util.Map;

import abstractions.immutable.context.gameplay.game.board.cell.BoardCellInterface;
import abstractions.immutable.rendering.board.cell.piece.PieceStringRendering;

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