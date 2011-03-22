package fr.designpattern.zerosumgames.extensions.checkers.pieces;

import java.util.List;

import fr.designpattern.zerosumgames.framework.build.game.components.board.GameBoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.build.game.components.board.cell.IGameBoardCell;

public interface ICheckersPiece {

	List<GameBoardCardinalPosition> getJumpOptions(IGameBoardCell cell);

	List<GameBoardCardinalPosition> getWalkOptions(IGameBoardCell cell);

	boolean isPromotable(IGameBoardCell cell);

}