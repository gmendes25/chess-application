package application;

import java.util.Scanner;

import application.chess.ChessMatch;
import application.chess.ChessPiece;
import application.chess.ChessPosition;

public class Program {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        ChessMatch chessMatch = new ChessMatch();
        while(true){
        UI.printBoard(chessMatch.getPieces());
        System.out.println();
        System.out.println("Source: ");
        ChessPosition source = UI.readChessPosition(sc);

        System.out.println();
        System.out.println("Target: ");
        ChessPosition target = UI.readChessPosition(sc);

        ChessPiece capturedChessPiece = chessMatch.performChessMove(source, target);
        }
    }
    
}

