
package fr.designpattern.zerosumgames.abstractions.immutable;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.AdversityInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.PlayerInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.PieceInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.type.PieceType;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.Move;
import fr.designpattern.zerosumgames.abstractions.immutable.move.MoveInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.BoardMutation;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.rendering.board.BoardConsoleRendering;
import fr.designpattern.zerosumgames.abstractions.immutable.rendering.board.BoardRenderer;
import fr.designpattern.zerosumgames.abstractions.immutable.rendering.board.BoardRenderingInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.rendering.board.BoardStringRendering;
import fr.designpattern.zerosumgames.abstractions.immutable.rendering.board.cell.BoardCellStringRendering;
import fr.designpattern.zerosumgames.abstractions.immutable.rendering.board.cell.piece.PieceStringRendering;
import fr.designpattern.zerosumgames.abstractions.immutable.util.CachingFactoriesUsage;
import fr.designpattern.zerosumgames.concretisations.reversi.context.pieces.ReversiPawn;

public final class ContextManager implements ContextManagerInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final ContextInterface context;

    /*-------------------------------------8<-------------------------------------*/

    private final Map<Object, Object> symbols;
    private final BoardRenderer boardRenderer;

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public ContextInterface context() {
        return this.context;
    }

    /*-------------------------------------8<-------------------------------------*/

    // TODO injecter une map immutable
    // TODO de toute façon, ce n'est pas vraiment la responsabilité du ContextManager de faire le rendu
    // TODO ?? utiliser un acteur pour faire le rendu de la vue
    public static ContextManagerInterface from(final ContextInterface context, final Map<Object, Object> symbols) {
        return new ContextManager(context, symbols);
    }

    /*-------------------------------------8<-------------------------------------*/

    private ContextManager(final ContextInterface context, final Map<Object, Object> symbols) {

        this.context = context;

        this.symbols = symbols;

        final BoardRenderingInterface<?> renderingType =
                new BoardConsoleRendering(
                        new BoardStringRendering(
                                new BoardCellStringRendering(
                                        new PieceStringRendering())));
        this.boardRenderer = new BoardRenderer(renderingType);

    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public ContextManagerInterface apply() {
        return this;
    }

    @Override
    public ContextManagerInterface apply(final ContextInterface context, final Map<Object, Object> symbols) {
        return new ContextManager(context, symbols);
    }

    /*-------------------------------------8<-------------------------------------*/

    private final void renderBoard(final BoardInterface board) {
        this.boardRenderer.render(board, this.symbols);
    }

    /*-------------------------------------8<-------------------------------------*/

    private final ContextInterface startFrom(final ContextInterface context) {
        /*-------------------------------------8<-------------------------------------*/
        if (context.isOver()) return context;
        /*-------------------------------------8<-------------------------------------*/
        this.renderBoard(context.game().board());
        /*-------------------------------------8<-------------------------------------*/
        final List<MoveTypeInterface> playableMoves = context.playableMoves();
        /*-------------------------------------8<-------------------------------------*/
        // TODO utiliser plutot un décorateur de cellule
        final Map<PositionInterface, PieceInterface> map = Maps.newHashMap();
        for (final MoveTypeInterface moveType : playableMoves) {
            final PositionInterface position = moveType.value().position();
            map.put(position, context.game().board().cell(position).value().apply(PieceType.from(ReversiPawn.class)));
        }
        System.out.println("\nLegal moves for " + context.side() + " : ");
        this.renderBoard(context.game().board().apply(BoardMutation.from(map)));
        /*-------------------------------------8<-------------------------------------*/
        final AdversityInterface adversity = context.adversity();
        final PlayerInterface<MoveTypeInterface> player = adversity.player(context.side());

        //System.out.println(player);

        final List<MoveTypeInterface> choosenMoves = player.playFrom(context);
        /*-------------------------------------8<-------------------------------------*/
        //System.out.println(choosenMoves);
        final MoveTypeInterface moveType = choosenMoves.get(0);
        /*-------------------------------------8<-------------------------------------*/
        //System.out.println(moveType);
        final MoveInterface move = Move.from(moveType, moveType.value().boardMutation(/*context.side(), context.game().board()*/));
        /*-------------------------------------8<-------------------------------------*/
        final long t0 = System.currentTimeMillis();
        while (System.currentTimeMillis() - t0 < 100)
            ;
        /*-------------------------------------8<-------------------------------------*/
        return this.startFrom(context.play(move).apply(context.side().opposite()));
        /*-------------------------------------8<-------------------------------------*/
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public void start() {

        System.out.println("START");

        final long t0 = System.currentTimeMillis();
        final ContextInterface finalContext = this.startFrom(this.context());
        final long t1 = System.currentTimeMillis();

        this.renderBoard(finalContext.game().board());

        System.out.println("\n--------------------8<--------------------\n");
        System.out.println("Game Over !");
        System.out.println("Temps de la partie : " + (t1 - t0) / 1000 + " secondes");
        final double scoreGame = (1 + Math.abs(finalContext.getHeuristicEvaluation())) * 10;
        System.out.println("Note du résultat de la partie : " + NumberFormat.getInstance().format(scoreGame) + " / 20");
        System.out.println("\n-------------------->8--------------------\n");

        CachingFactoriesUsage.show();

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    /*-------------------------------------8<-------------------------------------*/

}