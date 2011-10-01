
package fr.designpattern.zerosumgames.abstractions.context.adversity;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import fr.designpattern.zerosumgames.abstractions.context.adversity.player.PlayerInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.piece.side.Side;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.move.MoveInterface;

public final class Adversity implements AdversityInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final Map<SideInterface, PlayerInterface<MoveInterface>> players;

    /*-------------------------------------8<-------------------------------------*

    // TODO ? init à partir du player nul et side nulle
    // TODO ? gérer un ensemble de joueurs plus grand que 2
    private void copy(final PlayerInterface<MoveTypeInterface>[] players) {
        int ordinal = 1;
        final int side = 1;
        for (final PlayerInterface<MoveTypeInterface> player : players) {
            this.players.put(Side.from(ordinal * side), player);
            this.players.put(Side.from(ordinal * -side), player);
            ordinal += 1;
        }
    }

    /*-------------------------------------8<-------------------------------------*/

    public static AdversityInterface from(final PlayerInterface<MoveInterface>[] players) {
        return new Adversity(players[0], players[1]); // TODO check + gérer plusieurs joueurs
    }

    //private Adversity(final PlayerInterface<MoveTypeInterface>... players) {
    private Adversity(final PlayerInterface<MoveInterface> player1, final PlayerInterface<MoveInterface> player2) {
        this.players = Maps.newHashMap();
        final SideInterface side = Side.from(1);
        this.players.put(side, player1);
        this.players.put(side.opposite(), player2);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public AdversityInterface apply() {
        return this;
    }

    public AdversityInterface apply(final PlayerInterface<MoveInterface>... players) {
        // TODO gérer le cas ou l'input est null
        return new Adversity(players[0], players[1]); // TODO check + gérer plusieurs joueurs
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public PlayerInterface<MoveInterface> player(final SideInterface side) {
        // TODO gérer le cas ou l'input est null
        return this.players.get(side);
        // TODO gérer le cas ou l'output est nul        
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        for (final Entry<SideInterface, PlayerInterface<MoveInterface>> opponent : this.players.entrySet()) {
            sb.append(opponent.getKey());
            sb.append(opponent.getValue());
        }
        return sb.toString();
    }
    /*-------------------------------------8<-------------------------------------*/

}