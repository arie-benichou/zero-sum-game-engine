package fr.designpattern.zerosumgames.framework.gameplay.opponents;

import java.util.HashMap;
import java.util.Map;

import fr.designpattern.zerosumgames.framework.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.OpponentInterface;

// TODO ! définir GamePlayersEnumeration à l'intérieur de cette classe
public class Opponents implements OpponentsInterface {
	
	// ---------------------------------------------------------------------
	private transient Map<OpponentsEnumeration, OpponentInterface> opponents;
	private final Map<OpponentsEnumeration, OpponentInterface> getOpponents() {
		return this.opponents;
	}
	private final void setOpponents(Map<OpponentsEnumeration, OpponentInterface> opponents) {
		this.opponents = opponents;
	}
	// ---------------------------------------------------------------------
	private transient GameInterface context;
	private final void setContext(GameInterface context) {
		this.context = context;
	}
	public final GameInterface getContext() {
		return this.context;
	}	
	// ---------------------------------------------------------------------	
	public Opponents(final OpponentInterface opponentToSecondPlayer, final OpponentInterface opponentToFirstPlayer) {
		this.setOpponents(new HashMap<OpponentsEnumeration, OpponentInterface>(2));
		this.getOpponents().put(OpponentsEnumeration.FIRST_PLAYER, opponentToSecondPlayer);
		this.getOpponents().put(OpponentsEnumeration.SECOND_PLAYER, opponentToFirstPlayer);
	}
	// ---------------------------------------------------------------------
	public final OpponentInterface getOpponentByOrder(final OpponentsEnumeration playerOrdinal) {
		return this.getOpponents().get(playerOrdinal);
	}
	// ---------------------------------------------------------------------		
	public void injectContext(final GameInterface context) {
		this.setContext(context);
		this.getOpponentByOrder(OpponentsEnumeration.FIRST_PLAYER).setContext(this.getContext());
		this.getOpponentByOrder(OpponentsEnumeration.SECOND_PLAYER).setContext(this.getContext());
	}
	// ---------------------------------------------------------------------
	@Override
	public String toString() {
		return this.getOpponentByOrder(OpponentsEnumeration.FIRST_PLAYER) + " Vs " + this.getOpponentByOrder(OpponentsEnumeration.SECOND_PLAYER);
	}
	// ---------------------------------------------------------------------	
}