/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package abstractions.game;

import static abstractions.side.API.*;

import java.util.List;

import com.google.common.collect.Lists;

import abstractions.board.API.BoardInterface;
import abstractions.cell.API.CellInterface;
import abstractions.move.API.MoveInterface;
import abstractions.side.API.SideInterface;

/**
 * This class provides a skeletal implementation of the Game interface, to
 * minimize the effort required to implement this interface.
 */
public abstract class AbstractGame implements GameInterface {

    private final transient BoardInterface board;

    protected final BoardInterface getBoard() {
        return this.board;
    }

    public AbstractGame(final BoardInterface board) {
        // TODO checkNotNull
        this.board = board;
    }
    
    public final CellInterface cell(final int rowIndex, final int ColumnIndex) {
        return this.getBoard().getCell(rowIndex, ColumnIndex);
    }    

    @Override
    public final String toString() {
        return this.getClass().getSimpleName() + this.getBoard().toString();
    }
    
    // TODO ! proxy vers le board
    // TODO ! renommer en getLegalMoves dans le contexte du game
    public final List<CellInterface> getMutableCells(final SideInterface side) {
        List<CellInterface> mutableCells = Lists.newArrayList();
        for(CellInterface cell : this.getBoard()) {
            if(cell.isMutable(side)) {
                mutableCells.add(cell);
            }
        }
        return mutableCells;
    }

    public final SideInterface getNextSideToPlay(final MoveInterface playedMove, final boolean isMoveDone) {
        // TODO checkNotNull
        // TODO checkNotNull

        SideInterface nexSideInterfaceToPlay;

        if (!isMoveDone) {
            nexSideInterfaceToPlay = playedMove.getPiece().getSide();
        }
        else if (this.isGameOverFromVictory(playedMove)) {
            nexSideInterfaceToPlay = playedMove.getPiece().getSide().getNextSide().getNegation();
        }
        else if (this.isGameOverFromDraw(playedMove)) {
            nexSideInterfaceToPlay = NULL_SIDE;
        }
        else {
            nexSideInterfaceToPlay = playedMove.getPiece().getSide().getNextSide();
        }

        return nexSideInterfaceToPlay;

    }

    public final SideInterface play(final MoveInterface moveToPlay) {
        // TODO checkNotNull
        return this.getNextSideToPlay(moveToPlay, this.doMove(moveToPlay));
    }

    public abstract boolean hasNullMove();

    public abstract List<MoveInterface> getLegalMoves(SideInterface side);

    public abstract boolean doMove(MoveInterface moveToPlay);

    public abstract boolean undoMove(MoveInterface playedMove);

    public abstract boolean isGameOverFromVictory(MoveInterface playedMove);

    public abstract boolean isGameOverFromDraw(MoveInterface playedMove);

}