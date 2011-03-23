package fr.designpattern.zerosumgames.samples.checkers.pieces;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.BoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.CellInterface;

public interface ICheckersPiece {

	List<BoardCardinalPosition> getJumpOptions(CellInterface cell);

	List<BoardCardinalPosition> getWalkOptions(CellInterface cell);

	boolean isPromotable(CellInterface cell);

}