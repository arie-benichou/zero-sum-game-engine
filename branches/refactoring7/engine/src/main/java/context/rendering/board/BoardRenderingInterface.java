
package context.rendering.board;

import java.util.Map;

import context.entity.game.board.BoardInterface;
import context.rendering.RenderingInterface;

public interface BoardRenderingInterface<OUTPUT> extends RenderingInterface<BoardInterface, OUTPUT> {

    @Override
    public OUTPUT render(BoardInterface board);

    // TODO ajouter à RenderingInterface
    OUTPUT render(BoardInterface board, Map<Object, Object> symbols);

}