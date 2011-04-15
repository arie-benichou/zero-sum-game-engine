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

package fr.designpattern.zerosumgames.samples.checkers;

import java.util.ArrayList;
import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.AbstractGame;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.GameBoardInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.BoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.BoardDimensions;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.pieces.PieceInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.pieces.Pieces;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.positions.PositionInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.samples.checkers.pieces.CheckersPiece;

public class Checkers extends AbstractGame {

    // ------------------------------------------------------------
    public static final Class<CheckersPieceTypes> PIECE_TYPES = CheckersPieceTypes.class;
    public static final BoardDimensions BOARD_DIMENSION = new BoardDimensions(1, 8, 1, 8);

    // ------------------------------------------------------------
    public Checkers(final GameBoardInterface board) {
        // TODO !! à revoir
        super(new Pieces(Checkers.PIECE_TYPES), board);
        this.setupBoard(board);
    }

    // ------------------------------------------------------------
    private void setupBoard(final GameBoardInterface board) {
        int n, clientColumnIndex;
        for (int clientRowIndex = 1; clientRowIndex <= 3; ++clientRowIndex) {
            for (n = 1; n <= 4; ++n) {
                clientColumnIndex = 2 * n + clientRowIndex % 2 - 1;
                board.cell(clientRowIndex, clientColumnIndex).setPiece(
                        this.piece(OpponentsEnumeration.SECOND_PLAYER,
                                CheckersPieceTypes.MAN));
            }
        }
        // TODO permettre d'autres dimensions
        for (int clientRowIndex = 6; clientRowIndex <= 8; ++clientRowIndex) {
            for (n = 1; n <= 4; ++n) {
                clientColumnIndex = 2 * n + clientRowIndex % 2 - 1;
                board.cell(clientRowIndex, clientColumnIndex).setPiece(
                        this.piece(OpponentsEnumeration.FIRST_PLAYER,
                                CheckersPieceTypes.MAN));
            }
        }
    }

    // -----------------------------------------------------------------
    private List<BoardCellInterface> getRelevantCells(final OpponentsEnumeration side) {
        final List<BoardCellInterface> relevantCells = new ArrayList<BoardCellInterface>();
        for (final BoardCellInterface[] line : this.getBoard()) {
            for (final BoardCellInterface cell : line) {
                // TODO ? utiliser la pièce nulle
                if (!cell.isEmpty() && cell.getPiece().getSide() == side) {
                    relevantCells.add(cell);
                }
            }
        }
        return relevantCells;
    }

    // ------------------------------------------------------------
    @Override
    public boolean hasNullMove() {
        return false;
    }

    // -----------------------------------------------------------------
    private LegalMoveInterface makeMove(final OpponentsEnumeration side,
            final BoardPositionInterface position,
            final BoardCardinalPosition direction) {
        // TODO utiliser un cache
        return new CheckersMove(side, position, direction);
    }

    // ------------------------------------------------------------
    @Override
    public List<LegalMoveInterface> getLegalMoves(
            final OpponentsEnumeration side) {
        final List<LegalMoveInterface> jumpingMoves = new ArrayList<LegalMoveInterface>();
        CheckersPiece piece;
        List<BoardCardinalPosition> pieceOptions;

        // TODO chaque jeu doit définir son nullMove
        if (!previousMove.isNull()) {
            final CheckersMove previousCheckersMove = (CheckersMove) previousMove;
            if (!previousCheckersMove.isDone()) {
                //System.out.println("Tu n'as pas fini ton coup, celà réduit les coups légaux possibles...");
                //System.out.println(previousCheckersMove.getPosition());
                //System.out.println(previousCheckersMove.getDirection());
                final BoardCellInterface cell = this
                        .cell(previousCheckersMove.getPosition())
                        .getNeighbour(previousCheckersMove.getDirection())
                        .getNeighbour(previousCheckersMove.getDirection());
                piece = (CheckersPiece) cell.getPiece();
                pieceOptions = piece.getJumpOptions(cell);
                for (final BoardCardinalPosition direction : pieceOptions) {
                    jumpingMoves.add(this.makeMove(side, cell.getPosition(),
                            direction));
                }
                return jumpingMoves;
            }
        }

        final List<LegalMoveInterface> walkingMoves = new ArrayList<LegalMoveInterface>();
        boolean hasToJump = false;

        for (final BoardCellInterface cell : this.getRelevantCells(side)) {
            piece = (CheckersPiece) cell.getPiece();
            pieceOptions = piece.getJumpOptions(cell);
            if (!pieceOptions.isEmpty()) {
                hasToJump = true;
                for (final BoardCardinalPosition direction : pieceOptions) {
                    jumpingMoves.add(this.makeMove(side, cell.getPosition(),
                            direction));
                }
                continue;
            }
            if (!hasToJump) {
                pieceOptions = piece.getWalkOptions(cell);
                for (final BoardCardinalPosition direction : pieceOptions) {
                    walkingMoves.add(this.makeMove(side, cell.getPosition(),
                            direction));
                }
            }
        }

        return jumpingMoves.isEmpty() ? walkingMoves : jumpingMoves;
    }

    // ------------------------------------------------------------
    private boolean hasToKeepPlaying(final CheckersMove move) {
        BoardCellInterface actualCell = this.cell(move.getPosition());
        actualCell = actualCell.getNeighbour(move.getDirection());
        actualCell = actualCell.getNeighbour(move.getDirection());
        final CheckersPiece piece = (CheckersPiece) actualCell.getPiece();
        //Est-ce que la pièce peut encore sauter ?
        return !piece.getJumpOptions(actualCell).isEmpty();
    }

    // -----------------------------------------------------------------
    @Override
    public boolean doMove(final LegalMoveInterface moveToPlay) {
        final CheckersMove checkersMove = (CheckersMove) moveToPlay;
        // récupération de la cellule corespondant à la position
        final BoardCellInterface cell = this.cell(checkersMove.getPosition());
        // récupération de la pièce à déplacer
        final CheckersPiece pieceToMove = (CheckersPiece) cell.getPiece();
        // suppression de la pièce à sa position actuelle
        cell.setPiece(null);
        // récupération de la cellulle correspondant à la direction choisie
        BoardCellInterface destinationCell = cell.getNeighbour(checkersMove
                .getDirection());
        // si la cellule n'est pas vide
        if (!destinationCell.isEmpty()) {
            // la pièce de cette cellule est supprimée
            checkersMove.setCapturedPiece(destinationCell.getPiece());
            destinationCell.setPiece(null); // TODO ? utiliser la pièce nulle
            // et la cellule de destination devient la suivante
            destinationCell = destinationCell.getNeighbour(checkersMove
                    .getDirection());
        }
        // la pièce concernée par le coup est "déplacée" à sa cellule de destination
        destinationCell.setPiece(pieceToMove);
        checkersMove.hasBeenCrowned(false);
        // Si la pièce est un pion promotable
        if (pieceToMove.isPromotable(destinationCell)) {
            checkersMove.hasBeenCrowned(true);
            // le pion est promu roi
            destinationCell.setPiece(this.piece(checkersMove.getSide(),
                    CheckersPieceTypes.KING));
        }

        checkersMove.isDone(checkersMove.getCapturedPiece() == null
                || checkersMove.hasBeenCrowned()
                || !this.hasToKeepPlaying(checkersMove));

        return checkersMove.isDone();// TODO virer le return boolean de doMove et undoMove
    }

    // -----------------------------------------------------------------
    // TODO un move est un composiste de transistions de jeu
    // Pour Checkers, les transistions possibles sont :
    //une pièce se déplace d'une case
    //une pièce capture une autre pièce
    //une pièce se fait promouvoir en King
    //game over {victoire, match null}

    // -----------------------------------------------------------------
    @Override
    public boolean undoMove(final LegalMoveInterface move) {

        final CheckersMove checkersMove = (CheckersMove) move;

        BoardCellInterface cell = this.cell(move.getPosition()).getNeighbour(
                checkersMove.getDirection());

        if (checkersMove.getCapturedPiece() != null) {// TODO utiliser la pièce nulle
            cell.setPiece(checkersMove.getCapturedPiece());
            cell = cell.getNeighbour(checkersMove.getDirection());
        }

        final PieceInterface piece = checkersMove.hasBeenCrowned() ? this
                .piece(move.getSide(), CheckersPieceTypes.MAN) : cell
                .getPiece();
        this.cell(move.getPosition()).setPiece(piece);

        cell.setPiece(null); // TODO utiliser la pièce nulle

        return true; // TODO à virer
    }

    // -----------------------------------------------------------------
    // TODO à améliorer
    private boolean isGameOver(final LegalMoveInterface previousMove) {
        if (this.getRelevantCells(
                OpponentsEnumeration.opponent(previousMove.getSide()))
                .isEmpty()) {
            return true;
        }
        else
            if (this.getLegalMoves(
                    OpponentsEnumeration.opponent(previousMove.getSide()),
                    previousMove).isEmpty()) {
                return true;
            }
        return false;
    }

    // ------------------------------------------------------------
    // TODO à optimiser
    public int computeDelta(final OpponentsEnumeration side) {
        int delta = 0;
        for (final BoardCellInterface[] line : this.getBoard()) {
            for (final BoardCellInterface cell : line) {
                if (cell.isNull() || cell.isEmpty()) {
                    continue;
                }
                if (cell.getPiece().getSide().equals(side)) {
                    ++delta;
                    //System.out.println(cell.getPiece().getType());
                    if (cell.getPiece().getType() == CheckersPieceTypes.KING) {
                        delta += 2;
                        //System.out.println("King 1 count twice!");
                    }
                }
                else {
                    --delta;
                    //System.out.println(cell.getPiece().getType());
                    if (cell.getPiece().getType() == CheckersPieceTypes.KING) {
                        delta -= 2;
                        //System.out.println("King 2 count twice!");
                    }
                }
            }
        }
        return delta;
    }

    // ------------------------------------------------------------
    @Override
    public boolean isGameOverFromVictory(final LegalMoveInterface previousMove) {
        return this.isGameOver(previousMove)
                && this.computeDelta(previousMove.getSide()) != 0;
    }

    // ------------------------------------------------------------
    @Override
    public boolean isGameOverFromDraw(final LegalMoveInterface previousMove) {
        return this.isGameOver(previousMove)
                && this.computeDelta(previousMove.getSide()) == 0;
    }

    // ------------------------------------------------------------
    @Override
    public double computeStaticEvaluation(final LegalMoveInterface move) {
        return this.computeDelta(move.getSide());
    }
    // -----------------------------------------------------------------
}