
package context.rendering.board;

import java.util.Map;

import context.entity.game.board.BoardInterface;

public class BoardConsoleRendering implements BoardRenderingInterface<Void> {

    private final BoardStringRendering boardStringRendering;

    public BoardConsoleRendering(final BoardStringRendering boardStringRendering) {
        this.boardStringRendering = boardStringRendering;
    }

    @Override
    public Void render(final BoardInterface board) {
        return this.render(board, null);

    }

    @Override
    public Void render(final BoardInterface board, final Map<Object, Object> symbols) {
        System.out.println(this.boardStringRendering.render(board, symbols));
        return null;
    }

}