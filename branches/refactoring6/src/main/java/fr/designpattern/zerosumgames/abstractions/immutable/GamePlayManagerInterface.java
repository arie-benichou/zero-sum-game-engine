package fr.designpattern.zerosumgames.abstractions.immutable;

import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.GamePlayInterface;

public interface GamePlayManagerInterface extends ImmutableInterface<GamePlayManagerInterface>{

	public GamePlayInterface gameplay();

}