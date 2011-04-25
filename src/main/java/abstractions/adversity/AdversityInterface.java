
package abstractions.adversity;

import abstractions.player.PlayerInterface;
import abstractions.side.SideInterface;

public interface AdversityInterface {

    PlayerInterface getOpponent(final SideInterface side);

}
