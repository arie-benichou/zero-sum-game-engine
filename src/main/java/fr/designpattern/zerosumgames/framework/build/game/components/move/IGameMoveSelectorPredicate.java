package fr.designpattern.zerosumgames.framework.build.game.components.move;

import java.util.List;

// TODO utiliser les prédicats dans guava
public interface IGameMoveSelectorPredicate {

	boolean checkPredicate(List<IGameMove> legalMoves);

	void onTrue(List<IGameMove> legalMoves);

	void onFalse(List<IGameMove> legalMoves);

}