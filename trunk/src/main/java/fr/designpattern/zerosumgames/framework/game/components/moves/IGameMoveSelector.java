package fr.designpattern.zerosumgames.framework.game.components.moves;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.GameInterface;

public interface IGameMoveSelector {
	
	IGameMove select(GameInterface context, List<IGameMove> legalMoves);
}