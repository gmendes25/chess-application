package application.boardgame;

public abstract class Piece {
    
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
    
    public abstract boolean[][] possibleMoves();
    
    public boolean possibleMove(Position position){
    return possibleMoves()[position.getRow()][position.getColumn()];
    }
    
    public boolean isThereAnyPossibleMove(){
        boolean[][] mat = possibleMoves();
        for(int i = 0; i<mat.length;i++){
            for(int j = 0; j<mat.length;j++){
                if (mat[i][j]){
                    return true;
                }
            }
        }
        return false;
    }
}
