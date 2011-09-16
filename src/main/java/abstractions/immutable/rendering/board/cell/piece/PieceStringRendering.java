
package abstractions.immutable.rendering.board.cell.piece;

import java.util.Map;

import abstractions.immutable.context.gameplay.game.board.cell.piece.PieceInterface;

public class PieceStringRendering implements PieceRenderingInterface<String> {

    @Override
    public String render(final PieceInterface piece) {
        return this.render(piece, null);
    }

    public String render(final PieceInterface piece, final Map<Object, Object> symbols) {

        if (symbols != null) {
            final Object value = symbols.get(piece);
            if (value != null)
                return value.toString();
        }

        return piece.toString();
    }
}