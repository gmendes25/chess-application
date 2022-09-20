package application.chess;

import application.boardgame.Board;
import application.boardgame.Piece;

public abstract class ChessPiece extends Piece{
    //#region Attributes
    private Color color;
    //#endregion

    //#region Peça
    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }
    //#endregion

    //#region Getters and Setters
    public Color getColor() {
        return color;
    }
    //#endregion
    
}

