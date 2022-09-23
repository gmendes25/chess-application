package application.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import application.boardgame.Board;
import application.boardgame.BoardException;
import application.boardgame.Piece;
import application.boardgame.Position;
import application.chess.chesspieces.King;
import application.chess.chesspieces.Rook;

public class ChessMatch {
    //#region Attributes
    private int turn;

    private Color playerColor;

    private Board board;

    private boolean check;

    private boolean checkMate;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    
    private List<Piece> capturedPieces = new ArrayList<>();
    //#endregion

    //#region Getters and Setters
    public int getTurn(){
        return turn;
    }

    public Color getPlayerColor(){
        return playerColor;
    }

    public boolean getCheck(){
        return check;
    }

    public boolean getCheckMate(){
        return checkMate;
    }
    //#endregion

    //#region inicializador de partida

    public ChessMatch(){
        board = new Board(8,8);
        turn = 1;
        playerColor = Color.WHITE;
        check = false;
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
        piecesOnTheBoard.add(piece);
    }

    private Color opponent(Color color){
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color){
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
        for(Piece p : list){
            if (p instanceof King){
                return (ChessPiece)p;
            }
        }
        throw new IllegalStateException("There is no " + color + " king on the board.");
    }

    //#region Logica de Mate
    /**
     * Metodo que testa se o jogador está em check.
     * @param color Cor do jogador que será testado.
     * @return Retorna verdadeiro em caso de check.
     */
    private boolean testCheck(Color color){
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());
        for (Piece p : opponentPieces){
            boolean[][] mat = p.possibleMoves();
            if (mat[kingPosition.getRow()][kingPosition.getColumn()]){
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para avaliar as jogadas possiveis e informar se o jogador está em checkmate ou não.
     * @param color Cor do jogador que será testado.
     * @return Retorna verdadeiro em caso de checkmate.
     */
    private boolean testCheckMate(Color color){
        if(!testCheck(color)){
            return false;
        }

        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list){
            boolean[][] mat = p.possibleMoves();
            for(int i =0; i<board.getRows();i++){
                for(int j = 0; j<board.getColumns();j++){
                    if(mat[i][j]){
                        Position source = ((ChessPiece)p).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capturedPiece = makeMove(source, target);
                        boolean testCheck = testCheck(color);
                        undoMove(source, target, capturedPiece);
                        if(!testCheck){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    //#endregion

    //#region Movimentação das peças
    /**
     * Para imprimir as possiveis posições
     * @param sourcePosition Posição de origem
     * @return
     */
    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves(); 
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);

        if (testCheck(playerColor)){
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can't put yourself in check");
        }

        check = (testCheck(opponent(playerColor))) ? true : false;

        if(testCheckMate(opponent(playerColor))){
            checkMate = true;
        }else{
            nextTurn();
        }

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
        if(capturedPiece != null){
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturedPiece){
        Piece p = board.removePiece(target);
        board.placePiece(p, source);
        if(capturedPiece != null){
            board.placePiece(capturedPiece, target);
            piecesOnTheBoard.add(capturedPiece);
            capturedPieces.remove(capturedPiece);
        }
    }
    //#endregion

    //#region Validação de posicionamento
    /**
     * Utilizado para fazer a validação da posição, identificando se a peça está na posição selecionada.
     * @param position Posição que será validada.
     */
    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            throw new BoardException("There is no piece on source position.");
        }
        if(playerColor!= ((ChessPiece) board.piece(position)).getColor()){
            throw new ChessException("The chosen piece is not yours.");
        }
        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("There is no possible moves for the chosen piece.");
        }
    }

    private void validateTargetPosition(Position source, Position target){
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("The chosen piece can't move to target position.");
        }
    }
    
    private void nextTurn(){
        turn++;
        playerColor = (playerColor == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
    //#endregion

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
