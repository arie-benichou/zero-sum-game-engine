
package rendering.board.cell.piece;

import java.util.Map;

import abstractions.immutable.context.board.cell.piece.PieceInterface;

public class PieceStringRendering implements PieceRenderingInterface<String> {

    private final Map<PieceInterface, String> symbols;

    public PieceStringRendering(final Map<PieceInterface, String> symbols) {
        this.symbols = symbols;
    }

    @Override
    public String render(final PieceInterface piece) {
        return this.symbols.get(piece);
    }
}