package application.chess;

import application.boardgame.Board;
import application.boardgame.BoardException;
import application.boardgame.Piece;
import application.boardgame.Position;
import application.chess.chesspieces.King;
import application.chess.chesspieces.Rook;

public class ChessMatch {
    //#region Attributes
    private Board board;
    //#endregion

    //#region inicializador de partida
    public ChessMatch(){
        board = new Board(8,8);
        initialSetup();
    }
    //#endregion

    //#region Localizador de peças
    /**
     * Metodo para localizar as peças
     * @return peça encontrada
     */
    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for(int i =0; i<board.getRows();i++){
            for(int j = 0; j<board.getColumns();j++){
                mat[i][j] = (ChessPiece) board.piece(i,j);
            }
        }
        return mat;
    }
    //#endregion
    /**
     * Utilizado para posicionar uma peça.
     * @param column Coluna em que a peça será posicionada.
     * @param row Linha em que a peça será posicionada.
     * @param piece Qual é a peça que será posicionada.
     */
    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        Piece capturedPiece = makeMove(source, target);
        return (ChessPiece) capturedPiece;
    }

    /**
     * Utilizado para fazer o movimento de captura.
     * @param source Posição em que a peça será movida.
     * @param target Posição em que a peça movida será colocada.
     * @return Peça capturada.
     */
    private Piece makeMove(Position source, Position target){
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        return capturedPiece;
    }

    /**
     * Utilizado para fazer a validação da posição, identificando se a peça está na posição selecionada.
     * @param position Posição que será validada.
     */
    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            throw new BoardException("There is no piece on source position.");
        }
        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("There is no possible moves for the chosen piece.");
        }
    }
    
    //#region Posicionar peças
    /**
     * Metodo para colocar as peças na posição inicial de uma partida de xadrez
     */
    private void initialSetup(){
        //Rook position
        placeNewPiece('a',1, new Rook(board,Color.WHITE));
        placeNewPiece('a',8,new Rook(board,Color.BLACK));
        placeNewPiece('h',1,new Rook(board,Color.WHITE));
        placeNewPiece('h',8,new Rook(board,Color.BLACK));
        //King position
        placeNewPiece('e',1,new King(board,Color.WHITE));
        placeNewPiece('e',8,new King(board,Color.BLACK));
    }
    //#endregion
}
