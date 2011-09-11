
package rendering.board;

import java.util.Map;

import rendering.board.cell.BoardCellStringRendering;
import rendering.board.cell.piece.PieceStringRendering;
import abstractions.immutable.context.board.Board;
import abstractions.immutable.context.board.BoardInterface;
import abstractions.immutable.context.board.cell.piece.Piece;
import abstractions.immutable.context.board.cell.piece.PieceInterface;
import abstractions.immutable.context.board.cell.piece.side.Side;
import abstractions.immutable.context.board.cell.piece.type.PieceType;
import abstractions.immutable.context.board.cell.piece.type._Pawn;

import com.google.common.collect.Maps;

public class BoardRenderer /*implements BoardRenderingInterface<Void>*/{

    private final BoardRenderingInterface<?> renderingType;

    public BoardRenderer(final BoardRenderingInterface<?> renderingType) {
        this.renderingType = renderingType;
    }

    // @Override TODO ?? créer RendererInterface 
    public void render(final BoardInterface board) {
        this.renderingType.render(board);
        //return null; // TODO ? créer OutputInterface
    }

    public static void main(final String[] args) {

        final PieceInterface p0 = Piece.NULL;
        final PieceInterface p1 = Piece.from(Side.from(1), PieceType.from(_Pawn.class));
        final PieceInterface p2 = p1.apply(Side.from(-1));

        final Map<PieceInterface, String> symbols;
        symbols = Maps.newHashMap();
        symbols.put(p0, " ");
        symbols.put(p1, "x");
        symbols.put(p2, "o");

        final BoardInterface board = Board.from(3, 3);
        final BoardRenderingInterface<?> renderingType = new BoardConsoleRendering(new BoardStringRendering(new BoardCellStringRendering(
                new PieceStringRendering(symbols))));

        new BoardRenderer(renderingType).render(board);

    }
}