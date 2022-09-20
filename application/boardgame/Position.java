package application.boardgame;

public class Position{
    
    //#region Attributes
    private Integer row;

    private Integer column;
    //#endregion

    //#region Constructor
    /**
     * Indica a posição. Linha e 
     * @param row Linha
     * @param column Coluna
     */
    public Position(Integer row, Integer column) {
    this.column = column;
    this.row = row;
    }
    //#endregion
    
    //#region Getters and Setters
    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public void setValues(int row, int column){
        this.row = row;
        this.column = column;
    }
    //#endregion

    //#region toString
    @Override
    public String toString() {
        return "Position =" + row + " ," + column;
    }
    //#endregion
    
}