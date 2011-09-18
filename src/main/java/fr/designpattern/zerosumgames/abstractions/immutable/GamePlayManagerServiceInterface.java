package fr.designpattern.zerosumgames.abstractions.immutable;

import java.util.Map;

public interface GamePlayManagerServiceInterface {

	void start(final GamePlayManagerInterface gameplayManager, final Map<Object, Object> symbols);

	void pause();

	void resume();

	boolean isOver();

}
