package fr.designpattern.zerosumgames.core.interfaces;

import java.util.List;

public interface IMoveSelector {
	
	IGameBoardMove select(List<IGameBoardMove> legalMoves);
}