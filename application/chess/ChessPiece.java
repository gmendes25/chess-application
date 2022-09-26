package application.chess;

import application.boardgame.Board;
import application.boardgame.Piece;
import application.boardgame.Position;

public abstract class ChessPiece extends Piece{
    //#region Attributes
    private Color color;

    private int moveCount;
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

    public ChessPosition getChessPosition(){
        return ChessPosition.fromPosition(position);
    }

    public int getMoveCount(){
        return moveCount;
    }
    //#endregion

    protected void increaseMoveCount(){
        moveCount++;
    }
    protected void decreaseMoveCount(){
        moveCount--;
    }
    
    protected boolean isThereOpponentPiece(Position position){
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p.color != color;
    }
}

