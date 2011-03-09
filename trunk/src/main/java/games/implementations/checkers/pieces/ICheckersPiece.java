package main.java.implementations.checkers.pieces;

import java.util.List;

import main.java.core.interfaces.IGameBoardCell;
import main.java.core.types.GameBoardCardinalPosition;

public interface ICheckersPiece {

	List<GameBoardCardinalPosition> getJumpOptions(IGameBoardCell cell);

	List<GameBoardCardinalPosition> getWalkOptions(IGameBoardCell cell);

}