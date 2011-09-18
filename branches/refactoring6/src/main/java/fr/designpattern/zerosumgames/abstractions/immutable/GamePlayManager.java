package fr.designpattern.zerosumgames.abstractions.immutable;

import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.GamePlayInterface;


public final class GamePlayManager implements GamePlayManagerInterface {

	/*-------------------------------------8<-------------------------------------*/

	private final GamePlayInterface gameplay;

	/*-------------------------------------8<-------------------------------------*/

	public static GamePlayManagerInterface from(final GamePlayInterface gameplay) {
		return new GamePlayManager(gameplay);
	}

	private GamePlayManager(final GamePlayInterface gameplay) {
		this.gameplay = gameplay;
	}

	@Override
	public GamePlayManagerInterface apply() {
		return this;
	}

	/*-------------------------------------8<-------------------------------------*/

	@Override
	public GamePlayInterface gameplay() {
		return this.gameplay;
	}

	/*-------------------------------------8<-------------------------------------*/

}