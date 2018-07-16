package com.mycompany.battleship;


/**
 * Created by tuhinadasgupta on 7/8/18.
 */
public class TestBattleShip {
    public static void main(String[] args)
    {
        Board myBoard = new Board();
        Builder myBuilder = new Builder();
        myBuilder.build(myBoard);
        Player myPlayer = new Player();
        myPlayer.play(myBoard);

    }

}
