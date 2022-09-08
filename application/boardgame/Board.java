package application.boardgame;

public class Board {
    
    //#region Attributes
    private Integer rows;
    
    private Integer columns;

    private Piece[][] pieces;
    //#endregion

    //#region Constructor
    public Board(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
        this.pieces = new Piece[rows][columns];
    }
    //#endregion

    //#region Getters and Setters
    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }
    //#endregion
}
