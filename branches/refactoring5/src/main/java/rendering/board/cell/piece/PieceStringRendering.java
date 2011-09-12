
package rendering.board.cell.piece;

import java.util.Map;

import abstractions.immutable.context.board.cell.piece.PieceInterface;

public class PieceStringRendering implements PieceRenderingInterface<String> {

    @Override
    public String render(final PieceInterface piece) {
        return this.render(piece, null);
    }

    public String render(final PieceInterface piece, final Map<Object, Object> symbols) {
        return symbols == null ? piece.toString() : symbols.get(piece).toString();
    }
}