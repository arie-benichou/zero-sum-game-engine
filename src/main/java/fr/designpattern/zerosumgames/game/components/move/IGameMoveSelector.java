package fr.designpattern.zerosumgames.game.components.move;

import java.util.List;

import fr.designpattern.zerosumgames.game.IGame;

public interface IGameMoveSelector {
	
	IGameMove select(IGame context, List<IGameMove> legalMoves);
}