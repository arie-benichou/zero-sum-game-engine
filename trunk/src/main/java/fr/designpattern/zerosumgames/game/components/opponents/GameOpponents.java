package fr.designpattern.zerosumgames.game.components.opponents;

import java.util.HashMap;
import java.util.Map;

import fr.designpattern.zerosumgames.game.components.opponents.strategies.IGameStrategy;

// TODO ? définir GamePlayersEnumeration à l'intérieur de cette classe
public class GameOpponents implements IGameOpponents {
	
	Map<GamePlayersEnumeration, IGamePlayer> opponents = new HashMap<GamePlayersEnumeration, IGamePlayer>(2);
	
	public GameOpponents(IGamePlayer firstPlayer, IGamePlayer secondPlayer) {
		this.opponents.put(GamePlayersEnumeration.FIRST_PLAYER, firstPlayer);
		this.opponents.put(GamePlayersEnumeration.SECOND_PLAYER, secondPlayer);
	}

	private IGamePlayer getPlayer(GamePlayersEnumeration playerOrdinal) {
		return this.opponents.get(playerOrdinal);
	}

	public IGameStrategy getPlayerStrategy(GamePlayersEnumeration playerOrdinal) {
		return this.getPlayer(playerOrdinal).getStrategy();
	}

}