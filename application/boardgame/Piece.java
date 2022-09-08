package application.boardgame;

public class Piece {
    
    //#region Attributes
    protected Position position;
    
    private Board board;
    //#endregion
    
    //#region Getters and Setters
    protected Board getBoard() {
        return board;
    }
    //#endregion

    //#region Constructor
    public Piece(Board board) {
        this.board = board;
    }
    //#endregion
    
}
