
package fr.designpattern.zerosumgames.abstractions.context.game;

import java.util.List;

import fr.designpattern.zerosumgames.abstractions.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.referee.RefereeInterface;
import fr.designpattern.zerosumgames.abstractions.move.MoveInterface;

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

    /*
    @Override
    public boolean isGameOver(final SideInterface side) {
        return this.referee().isGamePlayOver(this.board(), side);
    }
    */

    @Override
    public List<MoveInterface> options(final ContextInterface context) {
        return this.referee().allowedOptions(context);
    }

    @Override
    public GameInterface apply(final MoveInterface move) {
        return this.apply(this.board().apply(move.value().boardMutation()));
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean isOver(final ContextInterface context) {
        return this.referee().isOver(context);
    }

}