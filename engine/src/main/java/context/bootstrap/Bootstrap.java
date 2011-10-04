
package context.bootstrap;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import context.Context;
import context.ContextInterface;
import context.entity.adversity.AdversityInterface;
import context.entity.adversity.player.PlayerInterface;
import context.entity.game.board.Board;
import context.entity.game.board.BoardInterface;
import context.entity.game.board.cell.BoardCell;
import context.entity.game.board.cell.piece.Piece;
import context.entity.game.board.cell.piece.side.Side;
import context.entity.game.board.cell.piece.type.PieceType;
import context.entity.game.board.cell.position.Position;
import context.entity.game.board.direction.Direction;
import context.entity.game.board.mutation.BoardMutation;
import context.event.Move;
import context.event.MoveInterface;
import context.rendering.board.BoardConsoleRendering;
import context.rendering.board.BoardRenderer;
import context.rendering.board.BoardRenderingInterface;
import context.rendering.board.BoardStringRendering;
import context.rendering.board.cell.BoardCellStringRendering;
import context.rendering.board.cell.piece.PieceStringRendering;

public final class Bootstrap implements BootstrapInterface {

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
    public static BootstrapInterface from(final ContextInterface context, final Map<Object, Object> symbols) {
        return new Bootstrap(context, symbols);
    }

    /*-------------------------------------8<-------------------------------------*/

    private Bootstrap(final ContextInterface context, final Map<Object, Object> symbols) {

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
    public BootstrapInterface apply() {
        return this;
    }

    @Override
    public BootstrapInterface apply(final ContextInterface context, final Map<Object, Object> symbols) {
        return new Bootstrap(context, symbols);
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
        /*        
        final List<MoveInterface> playableMoves = context.options();
        // TODO utiliser plutot un décorateur de cellule
        final Map<PositionInterface, PieceInterface> map = Maps.newHashMap();
        for (final MoveInterface moveType : playableMoves) {
            final PositionInterface position = moveType.value().position();
            map.put(position, context.game().board().cell(position).value().apply(PieceType.from(OthelloPawn.class)));
        }
        System.out.println("\nLegal moves for " + context.side() + " : ");
        this.renderBoard(context.game().board().apply(BoardMutation.from(map)));
        */
        /*-------------------------------------8<-------------------------------------*/
        final AdversityInterface adversity = context.adversity();
        final PlayerInterface<MoveInterface> player = adversity.player(context.side());
        final List<MoveInterface> choosenMoves = player.playFrom(context);
        /*-------------------------------------8<-------------------------------------*/
        final MoveInterface moveType = choosenMoves.get(0);
        /*-------------------------------------8<-------------------------------------*/
        final long t0 = System.currentTimeMillis();
        while (System.currentTimeMillis() - t0 < 100) {}
        /*-------------------------------------8<-------------------------------------*/
        return this.startFrom(context.apply(moveType).apply(context.side().opposite()));
        /*-------------------------------------8<-------------------------------------*/
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public void run() {

        System.out.println("START");

        final long t0 = System.currentTimeMillis();
        final ContextInterface finalContext = this.startFrom(this.context());
        final long t1 = System.currentTimeMillis();

        this.renderBoard(finalContext.game().board());

        System.out.println("\n--------------------8<--------------------\n");
        System.out.println("Game Over !");
        System.out.println("Temps de la partie : " + (t1 - t0) / 1000 + " secondes");
        final double scoreGame = (1 + Math.min(Math.abs(finalContext.evaluation()), 1)) * 10;
        System.out.println("Note du résultat de la partie : " + NumberFormat.getInstance().format(scoreGame) + " / 20");

        System.out.println();

        System.out.println("Direction.Factory       : " + Direction.Factory.cacheHits() + " / " + Direction.Factory.size());
        System.out.println("Side.Factory            : " + Side.Factory.cacheHits() + " / " + Side.Factory.size());
        System.out.println("PieceType.Factory       : " + PieceType.Factory.cacheHits() + " / " + PieceType.Factory.size());
        System.out.println("Piece.Factory           : " + Piece.Factory.cacheHits() + " / " + Piece.Factory.size());
        System.out.println("Position.Factory        : " + Position.Factory.cacheHits() + " / " + Position.Factory.size());
        System.out.println("BoardCell.Factory       : " + BoardCell.Factory.cacheHits() + " / " + BoardCell.Factory.size());
        System.out.println();
        System.out.println("Move instances          : " + Move.instances);
        System.out.println("BoardMutation instances : " + BoardMutation.instances);
        System.out.println("Board instances         : " + Board.instances);
        System.out.println("Context instances       : " + Context.instances);
        System.out.println("\n-------------------->8--------------------\n");

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