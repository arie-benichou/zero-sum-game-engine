package implementations.checkers.pieces;

import java.util.List;

import core.interfaces.IGameBoardCell;
import core.types.GameBoardCardinalPosition;

public interface ICheckersPiece {

	public List<GameBoardCardinalPosition> getJumpOptions(IGameBoardCell cell);

	public List<GameBoardCardinalPosition> getWalkOptions(IGameBoardCell cell);

}