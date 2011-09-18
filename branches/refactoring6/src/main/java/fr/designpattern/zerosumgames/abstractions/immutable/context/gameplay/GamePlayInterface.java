
package fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.adversity.AdversityInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;

public interface GamePlayInterface extends ImmutableInterface<GamePlayInterface> {

	/*-------------------------------------8<-------------------------------------*/

	SideInterface side();

	GamePlayInterface apply(SideInterface side);

	/*-------------------------------------8<-------------------------------------*/

	AdversityInterface adversity();

	GamePlayInterface apply(AdversityInterface adversity);

	/*-------------------------------------8<-------------------------------------*/

	GameInterface game();

	GamePlayInterface apply(GameInterface game);

	/*-------------------------------------8<-------------------------------------*/

}