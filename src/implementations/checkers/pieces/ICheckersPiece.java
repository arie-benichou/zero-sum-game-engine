package implementations.checkers.pieces;

import java.util.List;

import core.interfaces.IGameBoardCell;
import core.types.GameBoardCardinalPosition;

public interface ICheckersPiece {

	List<GameBoardCardinalPosition> getJumpOptions(IGameBoardCell cell);

	List<GameBoardCardinalPosition> getWalkOptions(IGameBoardCell cell);

}