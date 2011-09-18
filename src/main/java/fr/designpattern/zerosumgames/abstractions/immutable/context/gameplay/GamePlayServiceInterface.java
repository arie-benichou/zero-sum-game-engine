package fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay;

import java.util.List;

import fr.designpattern.zerosumgames.abstractions.immutable.move.MoveInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public interface GamePlayServiceInterface {

	boolean isGameOver(GamePlayInterface gamePlay);

	List<MoveTypeInterface> playableMoves(GamePlayInterface gamePlay);

	GamePlayInterface play(GamePlayInterface gamePlay, MoveInterface move);

}