package application.chess.chesspieces;

import application.boardgame.Board;
import application.boardgame.Position;
import application.chess.ChessPiece;
import application.chess.Color;

public class Rook extends ChessPiece{

    public Rook(Board board, Color color) {
        super(board, color);
    }

    //#region toString
    @Override
    public String toString() {
        return "R";
    }
    //#endregion

    //#region Movimentação da peça

    /**
     * Possiveis movimentos para a torre.
     */
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        
        Position p = new Position(0,0);

        //Up
        p.setValues(position.getRow() - 1, position.getColumn());
        while(getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
            p.setRow(p.getRow()-1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }
        
        //Left
        p.setValues(position.getRow(), position.getColumn()-1);
        while(getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
            p.setColumn(p.getColumn()-1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }

        //Down
        p.setValues(position.getRow() +1, position.getColumn());
        while(getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
            p.setRow(p.getRow()+1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }
        
        //Right
        p.setValues(position.getRow(), position.getColumn()+1);
        while(getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
            p.setColumn(p.getColumn()+1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }

        return mat;
    }
    //#endregion
}
