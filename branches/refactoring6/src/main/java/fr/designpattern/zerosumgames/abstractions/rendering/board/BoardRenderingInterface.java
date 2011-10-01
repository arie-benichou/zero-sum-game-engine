
package fr.designpattern.zerosumgames.abstractions.rendering.board;

import java.util.Map;

import fr.designpattern.zerosumgames.abstractions.context.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.rendering.RenderingInterface;

public interface BoardRenderingInterface<OUTPUT> extends RenderingInterface<BoardInterface, OUTPUT> {

    @Override
    public OUTPUT render(BoardInterface board);

    // TODO ajouter à RenderingInterface
    OUTPUT render(BoardInterface board, Map<Object, Object> symbols);

}