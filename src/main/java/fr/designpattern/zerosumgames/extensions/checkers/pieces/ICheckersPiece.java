package fr.designpattern.zerosumgames.extensions.checkers.pieces;

import java.util.List;

import fr.designpattern.zerosumgames.core.interfaces.IGameBoardCell;
import fr.designpattern.zerosumgames.core.types.GameBoardCardinalPosition;

public interface ICheckersPiece {

	List<GameBoardCardinalPosition> getJumpOptions(IGameBoardCell cell);

	List<GameBoardCardinalPosition> getWalkOptions(IGameBoardCell cell);

	boolean isPromotable(IGameBoardCell cell);

}