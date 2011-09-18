package fr.designpattern.zerosumgames.abstractions.immutable;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.GamePlayInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.GamePlayServiceInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.piece.PieceInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.piece.type.PieceType;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.position.PositionInterface;
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
import fr.designpattern.zerosumgames.concretisations.reversi.context.pieces.ReversiPawn;

public class GamePlayManagerService implements GamePlayManagerServiceInterface{

	/*-------------------------------------8<-------------------------------------*/

	private final GamePlayServiceInterface gameplayServive;

	/*-------------------------------------8<-------------------------------------*/

	public GamePlayManagerService(final GamePlayServiceInterface gameplayServive) {
		this.gameplayServive = gameplayServive;
	}

	/*-------------------------------------8<-------------------------------------*/

	private volatile boolean isPaused = false;

	/*-------------------------------------8<-------------------------------------*/

	private volatile boolean isOver = false;

	@Override
	public boolean isOver() {
		return this.isOver;
	}
	/*-------------------------------------8<-------------------------------------*/

	@Override
	public void start(final GamePlayManagerInterface gameplayManager, /*TODO utiliser Spring MVC pour le rendu */ final Map<Object, Object> symbols) {

		/*-------------------------------------8<-------------------------------------*/

		System.out.println("START");

		GamePlayInterface gameplay = gameplayManager.gameplay();

		/*-------------------------------------8<-------------------------------------*/

		// TODO utiliser Spring MVC pour le rendu

		final BoardRenderingInterface<?> renderingType =
				new BoardConsoleRendering(
						new BoardStringRendering(
								new BoardCellStringRendering(
										new PieceStringRendering())));
		final BoardRenderer boardRenderer = new BoardRenderer(renderingType);

		boardRenderer.render(gameplayManager.gameplay().game().board(), symbols);

		/*-------------------------------------8<-------------------------------------*/

		while(!this.gameplayServive.isGameOver(gameplay)) {

			System.out.println();

			/*-------------------------------------8<-------------------------------------*/

			final long t0 = System.currentTimeMillis();
			while (System.currentTimeMillis() - t0 < 100) ;

			/*-------------------------------------8<-------------------------------------*/

			final List<MoveTypeInterface> playableMoves = this.gameplayServive.playableMoves(gameplay);
			//System.out.println(playableMoves);

			/*-------------------------------------8<-------------------------------------*/

			// TODO utiliser plutot un décorateur de cellule

			final Map<PositionInterface, PieceInterface> map = Maps.newHashMap();
			for (final MoveTypeInterface moveType : playableMoves) {
				final PositionInterface position = moveType.value().position();
				map.put(position, gameplay.game().board().cell(position).value().apply(PieceType.from(ReversiPawn.class)));
			}

			System.out.println("\nLegal moves for " + gameplay.side() + " : ");
			boardRenderer.render(gameplay.game().board().apply(BoardMutation.from(map)), symbols);
			System.out.println();

			/*-------------------------------------8<-------------------------------------*/

			final MoveTypeInterface moveType = playableMoves.get(0);
			System.out.println(moveType);
			// TODO faire au moins un MoveService
			final MoveInterface move = Move.from(moveType, moveType.value().computeBoardMutation(gameplay.side(), gameplay.game().board()));
			gameplay = this.gameplayServive.play(gameplay, move);
			gameplay = gameplay.apply(gameplay.side().opposite());
			boardRenderer.render(gameplay.game().board(), symbols);

			System.out.println();


			while(this.isPaused);

		}

		System.out.println("over");

		this.isOver = true;
		/*-------------------------------------8<-------------------------------------*/

	}

	/*-------------------------------------8<-------------------------------------*/

	@Override
	public void pause() {
		System.out.println("PAUSE");
		this.isPaused = true;
	}

	@Override
	public void resume() {
		System.out.println("RESUME");
		this.isPaused = false;
	}

	/*-------------------------------------8<-------------------------------------*/

	//TODO pause() + actor()

}