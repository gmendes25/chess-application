package application.boardgame;

public class Board {
    
    //#region Attributes
    private Integer rows;
    
    private Integer columns;

    private Piece[][] pieces;
    //#endregion

    //#region Constructor
    /**
     * Cria um tabuleiro com uma determinada quantidade de linhas e colunas
     * @param rows Quantidade de Linhas
     * @param columns Quantidade de Colunas
     */
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

    //#region Return piece
    /**
     * Metodo para retornar a peça na linha e coluna determinada.
     * @param row Linha
     * @param column Coluna
     * @return Peça
     */
    public Piece piece(int row, int column){
        return pieces[row][column];
    }
    /**
     * Metodo para retornar a peça na posição determinada.
     * @param position Posição
     * @return Peça
     */
    public Piece piece(Position position){
        return pieces[position.getRow()][position.getColumn()];
    }
    //#endregion

    
}
