
package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.pieces;

import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

public class Piece extends AbstractPiece {

    public Piece(final OpponentsEnumeration side) {
        super(side);
        if (!side.isAPlayer()) {
            throw new IllegalArgumentException("Argument 'side' must be a player.");
        }
    }

    @Override
    public final boolean isNull() {
        return false;
    }

    @Override
    public String toString() {
        return this.getSide() == OpponentsEnumeration.FIRST_PLAYER ? "x" : "o";
    }

}