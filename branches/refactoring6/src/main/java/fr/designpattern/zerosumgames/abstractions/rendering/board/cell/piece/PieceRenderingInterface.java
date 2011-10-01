
package fr.designpattern.zerosumgames.abstractions.rendering.board.cell.piece;

import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.piece.PieceInterface;
import fr.designpattern.zerosumgames.abstractions.rendering.RenderingInterface;

public interface PieceRenderingInterface<OUTPUT> extends RenderingInterface<PieceInterface, OUTPUT> {

    @Override
    public OUTPUT render(PieceInterface piece);

}