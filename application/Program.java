package application;

import application.boardgame.Board;
import application.boardgame.Position;

public class Program {
    public static void main(String[] args){
        
        Position pos = new Position(3,5);
        Board board = new Board(8,8);
        System.out.println(pos);
        
    }

}

