package fr.designpattern.zerosumgames.tmp.game.components.opponents.strategies.selectors;

import java.util.List;

import fr.designpattern.zerosumgames.framework.moves.MoveInterface;

// TODO utiliser les prédicats dans guava
public interface MoveSelectorPredicateInterface {

	boolean checkPredicate(List<LegalMoveInterface> legalMoves);

	void onTrue(List<LegalMoveInterface> legalMoves);

	void onFalse(List<LegalMoveInterface> legalMoves);

}