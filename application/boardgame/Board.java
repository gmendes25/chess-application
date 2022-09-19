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
        if(rows < 1 || columns < 1){
            throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
        }
        this.rows = rows;
        this.columns = columns;
        this.pieces = new Piece[rows][columns];
    }
    //#endregion

    //#region Getters and Setters
    public Integer getRows() {
        return rows;
    }

    public Integer getColumns() {
        return columns;
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
        if (!positionExists(row, column)){
            throw new BoardException("Position not on the board");
        }
        return pieces[row][column];
    }
    /**
     * Metodo para retornar a peça na posição determinada.
     * @param position Posição
     * @return Peça
     */
    public Piece piece(Position position){
        if (!positionExists(position)){
            throw new BoardException("Position not on the board");
        }
        return pieces[position.getRow()][position.getColumn()];
    }
    //#endregion

    //#region Place Piece Method
    /**
     * Metodo para colocar a peça no tabuleiro
     * @param piece Peça
     * @param position Posição
     */
    public void placePiece(Piece piece, Position position){
        if (thereIsAPiece(position)){
            throw new BoardException("There is already a piece on position");
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }
    //#endregion
    
    private boolean positionExists(int row, int column){
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }
    public boolean positionExists(Position position){
       return positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position){
        if (!positionExists(position)){
            throw new BoardException("Position not on the board");
        }
        return piece(position) != null;
    }
}
