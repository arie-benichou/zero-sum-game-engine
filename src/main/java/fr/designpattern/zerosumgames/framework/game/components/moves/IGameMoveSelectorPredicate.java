package fr.designpattern.zerosumgames.framework.game.components.moves;

import java.util.List;

// TODO utiliser les prédicats dans guava
public interface IGameMoveSelectorPredicate {

	boolean checkPredicate(List<IGameMove> legalMoves);

	void onTrue(List<IGameMove> legalMoves);

	void onFalse(List<IGameMove> legalMoves);

}