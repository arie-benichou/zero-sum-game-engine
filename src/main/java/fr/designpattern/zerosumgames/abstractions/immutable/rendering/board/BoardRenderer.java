
package fr.designpattern.zerosumgames.abstractions.immutable.rendering.board;

import java.util.Map;

import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.BoardInterface;

public class BoardRenderer /*implements BoardRenderingInterface<Void>*/{

    private final BoardRenderingInterface<?> renderingType;

    public BoardRenderer(final BoardRenderingInterface<?> renderingType) {
        this.renderingType = renderingType;
    }

    // @Override TODO ?? créer RendererInterface 
    public void render(final BoardInterface board, final Map<Object, Object> symbols) {
        this.renderingType.render(board, symbols);
        //return null; // TODO ? créer OutputInterface
    }

    public void render(final BoardInterface board) {
        this.renderingType.render(board, null);
    }

    /*
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
    */
}