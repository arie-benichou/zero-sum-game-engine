
package fr.designpattern.zerosumgames.abstractions.context.adversity;

import fr.designpattern.zerosumgames.abstractions.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.context.adversity.player.PlayerInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.move.MoveInterface;

public interface AdversityInterface extends ImmutableInterface<AdversityInterface> {

    @Override
    public AdversityInterface apply();

    PlayerInterface<MoveInterface> player(SideInterface side);

}
