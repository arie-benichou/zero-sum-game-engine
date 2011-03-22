package fr.designpattern.zerosumgames.core.ai.heuristics;

import java.util.List;

import fr.designpattern.zerosumgames.core.interfaces.IGameBoardMove;

public interface IPredicate {

	boolean checkPredicate(List<IGameBoardMove> legalMoves);

	void onTrue(List<IGameBoardMove> legalMoves);

	void onFalse(List<IGameBoardMove> legalMoves);

}