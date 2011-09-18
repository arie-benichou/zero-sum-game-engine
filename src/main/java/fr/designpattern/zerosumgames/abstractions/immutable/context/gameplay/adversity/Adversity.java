
package fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.adversity;

import java.util.Map;
import java.util.Map.Entry;

import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.adversity.player.PlayerInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.piece.side.Side;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;

import com.google.common.collect.Maps;

public final class Adversity implements AdversityInterface {

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

    public static AdversityInterface from(final PlayerInterface[] players) {
        return new Adversity(players);
    }

    private Adversity(final PlayerInterface... players) {
        this.players = Maps.newHashMap();
        this.copy(players);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public AdversityInterface apply() {
        return this;
    }

    public AdversityInterface apply(final PlayerInterface... players) {
        // TODO ? gérer le cas ou l'input est null
        return new Adversity(players);
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