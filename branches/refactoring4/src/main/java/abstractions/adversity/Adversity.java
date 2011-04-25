
package abstractions.adversity;

import java.util.Map;

import abstractions.player.PlayerInterface;
import abstractions.side.SideInterface;
import abstractions.side.Sides;

import com.google.common.collect.Maps;

public class Adversity implements AdversityInterface {

    private final Map<SideInterface, PlayerInterface> map = Maps.newHashMapWithExpectedSize(2);

    public Adversity(final PlayerInterface firstSidePlayer, final PlayerInterface secondSidePlayer) {
        this.map.put(Sides.FIRST, firstSidePlayer);
        this.map.put(Sides.SECOND, secondSidePlayer);
    }

    public PlayerInterface getOpponent(final SideInterface side) {
        return this.map.get(side); // TODO ? gérer le null opponent ici
    }

    @Override
    public String toString() {
        return this.map.get(Sides.FIRST) + " Vs " + this.map.get(Sides.SECOND);
    }

}
