package fr.designpattern.zerosumgames.samples.checkers.pieces;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.board.GameBoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.game.components.board.cells.IGameBoardCell;

public interface ICheckersPiece {

	List<GameBoardCardinalPosition> getJumpOptions(IGameBoardCell cell);

	List<GameBoardCardinalPosition> getWalkOptions(IGameBoardCell cell);

	boolean isPromotable(IGameBoardCell cell);

}