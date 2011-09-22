
package fr.designpattern.zerosumgames.abstractions.immutable.context.adversity;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.PlayerInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.side.SideInterface;

public interface AdversityInterface extends ImmutableInterface<AdversityInterface> {

    @Override
    public AdversityInterface apply();

    PlayerInterface player(SideInterface side);

}
