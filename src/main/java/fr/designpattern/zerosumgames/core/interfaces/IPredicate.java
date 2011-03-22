package fr.designpattern.zerosumgames.core.interfaces;

import java.util.List;

// TODO utiliser les prédicats dans guava
public interface IPredicate {

	boolean checkPredicate(List<IGameBoardMove> legalMoves);

	void onTrue(List<IGameBoardMove> legalMoves);

	void onFalse(List<IGameBoardMove> legalMoves);

}