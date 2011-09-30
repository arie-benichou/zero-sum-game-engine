
package fr.designpattern.zerosumgames.abstractions.immutable.context.adversity;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.PlayerInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.MoveInterface;

public interface AdversityInterface extends ImmutableInterface<AdversityInterface> {

    @Override
    public AdversityInterface apply();

    PlayerInterface<MoveInterface> player(SideInterface side);

}
