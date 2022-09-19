package application.chess;

import application.boardgame.Board;
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
    
    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }


    //#region Posicionar peças
    /**
     * Metodo para colocar as peças na posição inicial de uma partida de xadrez
     */
    private void initialSetup(){
        //Rook position
        placeNewPiece('a',1, new Rook(board,Color.WHITE));
        placeNewPiece('a',8,new Rook(board,Color.WHITE));
        placeNewPiece('h',1,new Rook(board,Color.BLACK));
        placeNewPiece('h',8,new Rook(board,Color.BLACK));
        //King position
        placeNewPiece('e',1,new King(board,Color.BLACK));
        placeNewPiece('e',8,new King(board,Color.BLACK));
    }
    //#endregion
}
