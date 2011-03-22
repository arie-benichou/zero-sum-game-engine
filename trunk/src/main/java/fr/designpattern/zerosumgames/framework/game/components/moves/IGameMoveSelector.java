package fr.designpattern.zerosumgames.framework.game.components.moves;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.GameInterface;

public interface IGameMoveSelector {
	
	MoveInterface select(GameInterface context, List<MoveInterface> legalMoves);
}