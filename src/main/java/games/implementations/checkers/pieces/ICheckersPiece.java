package main.java.games.implementations.checkers.pieces;

import java.util.List;

import main.java.games.core.interfaces.IGameBoardCell;
import main.java.games.core.types.GameBoardCardinalPosition;

public interface ICheckersPiece {

	List<GameBoardCardinalPosition> getJumpOptions(IGameBoardCell cell);

	List<GameBoardCardinalPosition> getWalkOptions(IGameBoardCell cell);

}