package fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.referee.RefereeInterface;

public interface GameInterface extends ImmutableInterface<GameInterface> {

	/*-------------------------------------8<-------------------------------------*/

	BoardInterface board();

	GameInterface apply(BoardInterface board);

	/*-------------------------------------8<-------------------------------------*/

	RefereeInterface referee();

	GameInterface apply(RefereeInterface referee);

	/*-------------------------------------8<-------------------------------------*/

}