
package abstractions.immutable.context.gameplay;

import java.util.List;

import abstractions.immutable.context.adversity.AdversityInterface;
import abstractions.immutable.context.board.BoardInterface;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.game.GameInterface;
import abstractions.immutable.move.MoveInterface;
import abstractions.immutable.move.type.MoveTypeInterface;

public final class GamePlay implements GamePlayInterface {

    public static GamePlayInterface from(final SideInterface sideToPlay, final AdversityInterface adversity, final GameInterface game) {
        return new GamePlay(sideToPlay, adversity, game);
    }

    private final GameInterface game;
    private final AdversityInterface adversity;
    private final SideInterface sideToPlay;

    private GamePlay(final SideInterface sideToPlay, final AdversityInterface adversity, final GameInterface game) {
        this.game = game;
        this.adversity = adversity;
        this.sideToPlay = sideToPlay;
    }

    @Override
    public SideInterface sideToPlay() {
        return this.sideToPlay;
    }

    @Override
    public GameInterface game() {
        return this.game;
    }

    @Override
    public AdversityInterface adversity() {
        return this.adversity;
    }

    @Override
    public GamePlayInterface apply() {
        return this;
    }

    @Override
    public GamePlayInterface apply(final SideInterface sideToPlay) {
        return new GamePlay(sideToPlay, this.adversity(), this.game());
    }

    @Override
    public GamePlayInterface apply(final AdversityInterface adversity) {
        return new GamePlay(this.sideToPlay(), adversity, this.game());
    }

    @Override
    public GamePlayInterface apply(final GameInterface game) {
        return new GamePlay(this.sideToPlay(), this.adversity(), game);
    }

    private GamePlayInterface apply(final SideInterface side, final MoveInterface move) {
        return new GamePlay(side, this.adversity(), this.game.apply(move));
    }

    @Override
    public GamePlayInterface apply(final MoveInterface move) {
        return this.apply(this.sideToPlay(), move);
    }

    /*
    private List<MoveTypeInterface> computePlayableMoves(final SideInterface side, final BoardInterface board) {
        return this.game().computePlayableMoves(board, side);
    }
    */

    @Override
    public List<MoveTypeInterface> computePlayableMoves() {
        return this.game().computePlayableMoves(this.sideToPlay());
    }

    @Override
    public BoardInterface board() {
        return this.game().board();
    }

}
