
package abstractions.immutable.rendering.board;

import java.util.Map;

import abstractions.immutable.context.gameplay.game.board.BoardInterface;
import abstractions.immutable.rendering.RenderingInterface;

public interface BoardRenderingInterface<OUTPUT> extends RenderingInterface<BoardInterface, OUTPUT> {

    @Override
    public OUTPUT render(BoardInterface board);

    // TODO ajouter à RenderingInterface
    OUTPUT render(BoardInterface board, Map<Object, Object> symbols);

}