package com.mycompany.battleship;

/**
 * Created by tuhinadasgupta on 7/8/18.
 * A helper class that allows me to store coordinates (row,column) more conveniently
 */
public class Position {

     int row;
     int column;

    public Position(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

}
