package fr.designpattern.zerosumgames.framework.opponents.strategies.evaluators;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.GameInterface;
import fr.designpattern.zerosumgames.framework.game.components.moves.MoveInterface;

public class NullEvaluator implements EvaluatorInterface {

	private GameInterface context;
	public final void setContext(GameInterface context) {
		this.context = context;
	}
	public final GameInterface getContext() {
		return this.context;
	}

	public List<MoveInterface> applyEvaluation(List<MoveInterface> legalMoves) {
		return legalMoves;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
	
	public static void main(String[] args) {

		/*
		C'est le opponents qui doivent contenir le game et non l'inverse
		
		// TODO créer un objet GamePlay
		une partie de jeu = gamePlay {
		
			player1 = new player(name, strategy);
			player2 = new player(name, strategy);
			opponents = new opponents(player1,player2);
			
			...
			game = new Game(...);
			
			opponents.playAt(game);
		
		}
		
		le GameService utilise le GamePlay
		*/
		
	}
	
}

