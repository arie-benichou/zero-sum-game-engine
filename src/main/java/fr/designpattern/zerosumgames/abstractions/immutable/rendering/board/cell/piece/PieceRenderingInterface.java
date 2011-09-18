
package fr.designpattern.zerosumgames.abstractions.immutable.rendering.board.cell.piece;

import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.piece.PieceInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.rendering.RenderingInterface;

public interface PieceRenderingInterface<OUTPUT> extends RenderingInterface<PieceInterface, OUTPUT> {

    @Override
    public OUTPUT render(PieceInterface piece);

}