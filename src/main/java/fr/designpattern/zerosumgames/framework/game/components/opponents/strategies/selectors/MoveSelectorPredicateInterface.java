package fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors;

import java.util.List;

import fr.designpattern.zerosumgames.framework.moves.MoveInterface;

// TODO utiliser les prédicats dans guava
public interface MoveSelectorPredicateInterface {

	boolean checkPredicate(List<MoveInterface> legalMoves);

	void onTrue(List<MoveInterface> legalMoves);

	void onFalse(List<MoveInterface> legalMoves);

}