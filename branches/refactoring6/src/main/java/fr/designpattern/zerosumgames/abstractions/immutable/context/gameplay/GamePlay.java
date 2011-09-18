
package fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay;

import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.adversity.AdversityInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;


public final class GamePlay implements GamePlayInterface {

	/*-------------------------------------8<-------------------------------------*/

	private final GameInterface game;
	private final AdversityInterface adversity;
	private final SideInterface sideToPlay;

	/*-------------------------------------8<-------------------------------------*/

	public static GamePlayInterface from(final SideInterface sideToPlay, final AdversityInterface adversity, final GameInterface game) {
		return new GamePlay(sideToPlay, adversity, game);
	}

	private GamePlay(final SideInterface sideToPlay, final AdversityInterface adversity, final GameInterface game) {
		this.game = game;
		this.adversity = adversity;
		this.sideToPlay = sideToPlay;
	}

	@Override
	public GamePlayInterface apply() {
		return this;
	}

	/*-------------------------------------8<-------------------------------------*/

	@Override
	public SideInterface side() {
		return this.sideToPlay;
	}

	@Override
	public GamePlayInterface apply(final SideInterface sideToPlay) {
		return new GamePlay(sideToPlay, this.adversity(), this.game());
	}

	/*-------------------------------------8<-------------------------------------*/

	@Override
	public GameInterface game() {
		return this.game;
	}

	@Override
	public GamePlayInterface apply(final GameInterface game) {
		return new GamePlay(this.side(), this.adversity(), game);
	}

	/*-------------------------------------8<-------------------------------------*/

	@Override
	public AdversityInterface adversity() {
		return this.adversity;
	}

	@Override
	public GamePlayInterface apply(final AdversityInterface adversity) {
		return new GamePlay(this.side(), adversity, this.game());
	}

	/*-------------------------------------8<-------------------------------------*/

}