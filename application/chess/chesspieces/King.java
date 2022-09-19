package application.chess.chesspieces;

import application.boardgame.Board;
import application.chess.ChessPiece;
import application.chess.Color;

public class King extends ChessPiece{

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }
    
}
