package com.mycompany.battleship;


import java.util.ArrayList;
/**
 * Created by tuhinadasgupta on 7/8/18.
 */
public class Board {

    public int shipCount;
    public int row;
    public int column;
    public int maxMisses;
    public ArrayList<Position> positionList;
    public int[][] myBoard;

    public void setShipCount(int shipCount)
    {
        this.shipCount = shipCount;
    }
    public void setBoardDimensionsRow(int row)
    {
        this.row = row;
    }
    public void setBoardDimensionsColumn(int column)
    {
        this.column = column;
    }
    public void setBoardDimensionsBoth(int row, int column) {myBoard = new int[row][column];}
    public int getRow()
    {
        return row;
    }
    public int getColumn()
    {
        return column;
    }
    public void setMaxMisses(int maxMisses)
    {
        this.maxMisses = maxMisses;
    }

    /**
     * Sets the ship coordinates as defined by the builder
     * @param positionList the array list of type position that contains the ship(s) coordinates
     */
    public void setShipPosition(ArrayList<Position> positionList)
    {
        this.positionList = positionList;
        for (int i=0; i< positionList.size(); i++) {
            {
                Position position = positionList.get(i);
                int x = position.getRow();
                int y = position.getColumn();
                myBoard[x-1][y-1] =1;

            }
        }
    }

    /**
     * Checks to see if every coordinate is hit => resulting in the end of the game
     * @return boolean check that every coordinate has been hit by the player
     */
    public boolean shipSunk()
    {
        boolean returnValue = true;
        int counter = (row * column);
        for (int i=0; i<row; i++)
        {
            for (int j=0; j<column; j++)
            {
                if (myBoard[i][j] != 1)
                {
                    counter--;
                }
            }
        }
        if (counter==0)
        {
           returnValue = true;
        }
        else
        {
            returnValue = false;
        }
        return returnValue;
    }

    /**
     * Updates the board according to player's hits and misses
     * @param positionList1 the player input of strike coordinates in an array list of type position
     * @return boolean is true if the strike is a miss
     */
    public boolean setAttempt(ArrayList<Position> positionList1)
    {
        boolean returnValue = true;
        int arrayListSize = positionList1.size();
        for (int i = 0, j = 0; i < arrayListSize; i = i + 2, j++) {
            Position position = positionList1.get(j);
            int compareRow = position.getRow();
            int compareCol = position.getColumn();
            if(myBoard[compareRow-1][compareCol-1] == 0)
            {
                //miss
                myBoard[compareRow-1][compareCol-1] =3;
                returnValue = true;

            }
            if (myBoard[compareRow-1][compareCol-1] == 1)
            {
                //hit
                myBoard[compareRow-1][compareCol-1] =2;
                returnValue = false;
            }

        }
        return returnValue;

    }

    /**
     *prints out the game board according to the board's values
     *the builder board displays the ships (represented by *) and water (represented by -)
     * the player board displays hits (represented by X) and misses (represented by O)
     */

    public void printDisplay(String persona)
    {
        if(persona.equals("Builder"))
        {
            for (int i =0; i< row; i++)
            {
                for (int j=0; j<column; j++)
                {
                    if (myBoard[i][j] == 1)
                    {
                        System.out.print("*");
                        System.out.print("\t");
                    }
                    if (myBoard[i][j] == 0)
                    {
                        System.out.print("-");
                        System.out.print("\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }
        if (persona.equals("Player"))
        {
            for (int i=0; i<row; i++ )
            {
                for (int j=0; j<column; j++)
                {
                    if (myBoard[i][j] == 2)
                    {
                        System.out.print("X");
                        System.out.print("\t");
                    }
                     if (myBoard[i][j] == 3)
                    {
                        System.out.print("O");
                        System.out.print("\t");
                    }
                     if (myBoard[i][j] == 0 || myBoard[i][j]==1)
                    {
                        System.out.print("-");
                        System.out.print("\t");
                    }
                }
                System.out.println();
            }
        }
    }
}
