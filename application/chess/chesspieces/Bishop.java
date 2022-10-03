package application.chess.chesspieces;

import application.boardgame.Board;
import application.boardgame.Position;
import application.chess.ChessPiece;
import application.chess.Color;

public class Bishop extends ChessPiece {
 
    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        
        Position p = new Position(0,0);
        //UPLeft
        p.setValues(position.getRow()-1, position.getColumn()-1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
            p.setValues(p.getRow()-1, p.getColumn()-1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }
        //UPRight
        p.setValues(position.getRow()-1, position.getColumn()+1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
            p.setValues(p.getRow()-1, p.getColumn()+1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }
        //DOWNLeft
        p.setValues(position.getRow()+1, position.getColumn()-1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
            p.setValues(p.getRow()+1, p.getColumn()-1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }
        //DOWNRight   
        p.setValues(position.getRow()+1, position.getColumn()+1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
            p.setValues(p.getRow()+1, p.getColumn()+1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()]= true;
        }         
        return mat;
    }

    @Override
    public String toString() {
        return "B";
    }
    
    

}
