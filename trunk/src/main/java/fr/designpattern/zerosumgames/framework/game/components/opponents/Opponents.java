package fr.designpattern.zerosumgames.framework.game.components.opponents;

import java.util.HashMap;
import java.util.Map;

import fr.designpattern.zerosumgames.framework.game.components.opponents.players.PlayerInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.strategies.StrategyInterface;

// TODO ? définir GamePlayersEnumeration à l'intérieur de cette classe
public class Opponents implements OpponentsInterface {
	
	Map<OpponentsEnumeration, PlayerInterface> opponents = new HashMap<OpponentsEnumeration, PlayerInterface>(2);
	
	public Opponents(PlayerInterface firstPlayer, PlayerInterface secondPlayer) {
		this.opponents.put(OpponentsEnumeration.FIRST_PLAYER, firstPlayer);
		this.opponents.put(OpponentsEnumeration.SECOND_PLAYER, secondPlayer);
	}

	private PlayerInterface getPlayer(OpponentsEnumeration playerOrdinal) {
		return this.opponents.get(playerOrdinal);
	}

	public StrategyInterface getPlayerStrategy(OpponentsEnumeration playerOrdinal) {
		return this.getPlayer(playerOrdinal).getStrategy();
	}

}