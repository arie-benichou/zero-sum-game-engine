
package abstractions.game;

import java.util.List;

import abstractions.cell.CellInterface;
import abstractions.move.API.MoveInterface;
import abstractions.side.API.SideInterface;


/**
 * This is the interface for a game.
 */
public interface GameInterface {

    /**
     * Returns true if this game allows null move, false otherwise.
     * 
     * @return true if this game allows null move, false otherwise
     */
    boolean hasNullMove();
    
    
    List<CellInterface> getMutableCells(final SideInterface side);

    /**
     * Returns the list of legal moves for a given side.
     * 
     * @param side
     *            a given side
     * 
     * @return the list of legal moves for a given side
     */
    List<MoveInterface> getLegalMoves(final SideInterface side);

    /**
     * Plays a given move and returns the next side to play.
     * 
     * @param move
     *            a given move
     * 
     * @return the next side to play
     */
    SideInterface play(final MoveInterface move);

    /**
     * Applies a given move and returns true if the move is completed, false
     * otherwise.
     * 
     * @param move
     *            a given move
     * 
     * @return true if the move is completed, false otherwise
     */
    boolean doMove(final MoveInterface move);

    /**
     * Undo a given move and returns true if the move is completely undone,
     * false otherwise.
     * 
     * @param move
     *            the played move
     * 
     * @return true if the move is completely undone, false otherwise
     */
    boolean undoMove(final MoveInterface move);

    /**
     * Returns true if the game is over from a victory, false otherwise.
     * 
     * @param move
     *            the just played move
     * 
     * @return true if the game is over from a victory, false otherwise
     */
    boolean isGameOverFromVictory(final MoveInterface move);

    /**
     * Returns true if the game is over from a draw, false otherwise.
     * 
     * @param move
     *            the just played move
     * 
     * @return true if the game is over from a victory of the current player,
     *         false otherwise
     */
    boolean isGameOverFromDraw(final MoveInterface move);

    /**
     * Returns FIRST_PLAYER, if it's first player's turn, SECOND_PLAYER, if it's
     * second player's turn, NOT_FIRST_PLAYER if the second player is a winner,
     * NOT_SECOND_PLAYER, if the first player is a winner, NO_ONE, if the game
     * is a draw.
     * 
     * @param move
     *            the just played move
     * 
     * @param isMoveDone
     *            the boolean for the move completion
     * 
     * @return FIRST_PLAYER, if it's first player's turn, SECOND_PLAYER, if it's
     *         second player's turn, NOT_FIRST_PLAYER if the second player is a
     *         winner, NOT_SECOND_PLAYER, if the first player is a winner,
     *         NO_ONE, if the game is a draw
     */
    SideInterface getNextSideToPlay(final MoveInterface move, final boolean isMoveDone);

    /**
     * Returns the computation of the static evaluation for a given move.
     * 
     * @param move
     *            a given move
     * 
     * @return the computation of the static evaluation for a given move
     */
    //double computeStaticEvaluation(final MoveInterface move);

    // ---------------------------------------------------------------------
    // Façades
    // ---------------------------------------------------------------------

    /**
     * Returns a piece of this game for a given player and a given type of
     * piece.
     * 
     * @param player
     *            a given player
     * 
     * @param pieceType
     *            a given type of piece.
     * 
     * @return a piece of this game for a given player and a given type of piece
     */
    //PieceInterface piece(final SideInterface player,
    //        final PieceTypeInterface pieceType);

    /**
     * Returns the board cell related to a given position.
     * 
     * @param position
     *            a given position
     * 
     * @return the board cell related to a given position
     */
    //CellInterface cell(final PositionInterface position);

    /**
     * Returns the board cell related to a given row and a given column.
     * 
     * @param clientRowIndex
     *            a given row
     * 
     * @param clientColumnIndex
     *            a given column
     * 
     * @return the board cell related to a given row and a given column
     */
    CellInterface cell(final int clientRowIndex, final int clientColumnIndex);

}