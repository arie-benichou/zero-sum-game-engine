package fr.designpattern.zerosumgames.core.strategies.engines.heuristics;

import java.util.List;

import fr.designpattern.zerosumgames.core.interfaces.IGameBoardMove;

public interface IPredicate {

	boolean checkPredicate(List<IGameBoardMove> legalMoves);

	void onTrue(List<IGameBoardMove> legalMoves);

	void onFalse(List<IGameBoardMove> legalMoves);

}