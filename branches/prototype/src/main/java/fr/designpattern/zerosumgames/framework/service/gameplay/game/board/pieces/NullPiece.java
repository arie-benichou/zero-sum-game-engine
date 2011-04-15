
package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.pieces;

import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

public class NullPiece extends AbstractPiece {

    public NullPiece() {
        super(OpponentsEnumeration.NO_ONE);
    }

    @Override
    public final boolean isNull() {
        return true;
    }

    @Override
    public String toString() {
        return " ";
    }

}