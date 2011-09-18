
package fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game;

import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.referee.RefereeInterface;

public class Game implements GameInterface {

	/*-------------------------------------8<-------------------------------------*/

	private final BoardInterface board;
	private final RefereeInterface referee;

	/*-------------------------------------8<-------------------------------------*/

	public static GameInterface from(final BoardInterface board, final RefereeInterface referee) {
		return new Game(board, referee);
	}

	private Game(final BoardInterface board, final RefereeInterface referee) {
		this.board = board;
		this.referee = referee;
	}

	/*-------------------------------------8<-------------------------------------*/

	@Override
	public GameInterface apply() {
		return this;
	}

	/*-------------------------------------8<-------------------------------------*/

	@Override
	public GameInterface apply(final BoardInterface board) {
		return new Game(board, this.referee());
	}

	@Override
	public BoardInterface board() {
		return this.board;
	}

	/*-------------------------------------8<-------------------------------------*/

	@Override
	public RefereeInterface referee() {
		return this.referee;
	}

	@Override
	public GameInterface apply(final RefereeInterface referee) {
		return new Game(this.board(), referee);
	}

	/*-------------------------------------8<-------------------------------------*/

}