package fr.designpattern.zerosumgames.framework.game.components.moves;

import java.util.List;

// TODO utiliser les prédicats dans guava
public interface IGameMoveSelectorPredicate {

	boolean checkPredicate(List<MoveInterface> legalMoves);

	void onTrue(List<MoveInterface> legalMoves);

	void onFalse(List<MoveInterface> legalMoves);

}