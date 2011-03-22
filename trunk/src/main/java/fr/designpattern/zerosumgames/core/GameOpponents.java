package fr.designpattern.zerosumgames.core;

import java.util.HashMap;
import java.util.Map;

import fr.designpattern.zerosumgames.core.interfaces.IGameOpponents;
import fr.designpattern.zerosumgames.core.interfaces.IGamePlayer;
import fr.designpattern.zerosumgames.core.interfaces.IGamePlayerStrategy;
import fr.designpattern.zerosumgames.core.types.GamePlayersEnumeration;

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

	public IGamePlayerStrategy getPlayerStrategy(GamePlayersEnumeration playerOrdinal) {
		return this.getPlayer(playerOrdinal).getStrategy();
	}

}