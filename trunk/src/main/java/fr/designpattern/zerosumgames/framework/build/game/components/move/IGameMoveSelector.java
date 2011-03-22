package fr.designpattern.zerosumgames.framework.build.game.components.move;

import java.util.List;

import fr.designpattern.zerosumgames.framework.build.game.IGame;

public interface IGameMoveSelector {
	
	IGameMove select(IGame context, List<IGameMove> legalMoves);
}