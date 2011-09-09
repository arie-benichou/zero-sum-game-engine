
package abstractions.immutable.opponents;

import java.util.Map;
import java.util.Map.Entry;

import abstractions.immutable.player.PlayerInterface;
import abstractions.immutable.side.Side;
import abstractions.immutable.side.SideInterface;

import com.google.common.collect.Maps;

public final class Opponents implements OpponentsInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final Map<SideInterface, PlayerInterface> players;

    /*-------------------------------------8<-------------------------------------*/

    // TODO ? init à partir du player nul et side nulle
    private void copy(final PlayerInterface[] players) {
        int ordinal = 1;
        int side = 1;
        for (final PlayerInterface player : players) {
            this.players.put(Side.from(ordinal * side), player);
            ordinal += 1;
            side *= -1;
        }
    }

    /*-------------------------------------8<-------------------------------------*/

    public static OpponentsInterface from(final PlayerInterface[] players) {
        return new Opponents(players);
    }

    private Opponents(final PlayerInterface... players) {
        this.players = Maps.newHashMap();
        this.copy(players);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public OpponentsInterface apply() {
        return this;
    }

    public OpponentsInterface apply(final PlayerInterface... players) {
        // TODO ? gérer le cas ou l'input est null
        return new Opponents(players);
    }

    /*-------------------------------------8<-------------------------------------*/

    PlayerInterface player(final SideInterface side) {
        // TODO gérer le cas ou l'input est null
        return this.players.get(side);
        // TODO gérer le cas ou l'output est nul        
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        for (final Entry<SideInterface, PlayerInterface> opponent : this.players.entrySet()) {
            sb.append(opponent.getKey());
            sb.append(opponent.getValue());
        }
        return sb.toString();
    }
    /*-------------------------------------8<-------------------------------------*/

}