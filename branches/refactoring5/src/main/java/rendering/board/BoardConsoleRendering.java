
package rendering.board;

import abstractions.immutable.context.board.BoardInterface;

public class BoardConsoleRendering implements BoardRenderingInterface<Void> {

    private final BoardStringRendering boardStringRendering;

    public BoardConsoleRendering(final BoardStringRendering boardStringRendering) {
        this.boardStringRendering = boardStringRendering;
    }

    @Override
    public Void render(final BoardInterface board) {
        System.out.println(this.boardStringRendering.render(board));
        return null;
    }

}