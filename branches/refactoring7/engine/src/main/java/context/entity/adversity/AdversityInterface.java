
package context.entity.adversity;

import util.interfaces.ImmutableInterface;
import context.entity.adversity.player.PlayerInterface;
import context.entity.game.board.cell.piece.side.SideInterface;
import context.event.MoveInterface;

public interface AdversityInterface extends ImmutableInterface<AdversityInterface> {

    @Override
    public AdversityInterface apply();

    PlayerInterface<MoveInterface> player(SideInterface side);

}
