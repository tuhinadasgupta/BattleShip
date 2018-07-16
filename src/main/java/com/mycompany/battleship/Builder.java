package com.mycompany.battleship;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Created by tuhinadasgupta on 7/8/18.
 */
public class Builder {
    public void build(Board myBoard) {
        int notifyCounter = 0; // tracks number of ships
        int errorCounter = 0; // tracks incorrect/invalid input of ship coordinates
        printWelcomeBuilder();;
        int row = getUserBoardDimensionsRows();
        myBoard.setBoardDimensionsRow(row);
        int column = getUserBoardDimensionsColumns();
        myBoard.setBoardDimensionsColumn(column);
        myBoard.setBoardDimensionsBoth(row, column);
        int maxMisses = getMaxMisses();
        myBoard.setMaxMisses(maxMisses);
        int shipCount = getShipCount();
        myBoard.setShipCount(shipCount);

        for (int i = 0; i < myBoard.shipCount; i++) {
            notifyCounter++;
            ArrayList<Position> positionList = getShipPosition(notifyCounter);
            boolean coordinatesFit = checkCoordinates(myBoard, positionList);
            boolean checkH = checkHorizontal(positionList);
            boolean checkV = checkVertical(positionList);
            boolean checkD = checkDiagonal(positionList);
                if (coordinatesFit && (checkH|| checkV|| checkD))  {
                    myBoard.setShipPosition(positionList);
                }
                else
                {
                    errorCounter++;
                    while(errorCounter>= 1)
                    {
                        System.out.println("The coordinates entered don't form a ship... Please try again to enter a ship horizontally, vertically, or diagonally");
                        getShipPosition(notifyCounter);
                        if (checkCoordinates(myBoard, positionList))
                        {
                            errorCounter--;
                        }
                    }
                    myBoard.setShipPosition(positionList);
                }
            }
        System.out.println("Here's your board: ") ;
        String persona = "Builder";
        myBoard.printDisplay(persona);
    }

    /**
     * A welcome message for the builder of the board and ships
     */
    public void printWelcomeBuilder()
    {
        System.out.println("Welcome to Battleship!");
        System.out.println("You'll be defining your board and placing your ships! (* represent a ship coordinate) ");
        System.out.println("Your ship(s) must be orientedhorizontally, vertically, or diagonally! Have fun!!");
    }
    /**
     * Takes the user input number of rows
     * @return the number of rows
     */
    public int getUserBoardDimensionsRows() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the number of rows you want for your board: ");
        int row = sc.nextInt();
        while (row<=0)
        {
            System.out.println("Please enter the number of rows you want for your board: ");
            row = sc.nextInt();
        }
        return row;
    }

    /**
     * Takes the user input number of columns
     * @return the number of columns
     */
    public int getUserBoardDimensionsColumns() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the number of columns you want for your board: ");
        int column = sc.nextInt();
        while (column<=0)
        {
            System.out.println("Please enter the number of columns you want for your board: ");
            column = sc.nextInt();
        }
        return column;
    }

    /**
     * User input number of misses player has till the game ends
     * @return the number of max misses possible
     */
    public int getMaxMisses() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the max number of misses allowed: ");
        int maxMisses = sc.nextInt();
        while(maxMisses <0)
        {
            System.out.println("Please enter the max number of misses allowed: ");
            maxMisses = sc.nextInt();
        }
        return maxMisses;
    }

    /**
     * User input number of ships
     * @return the number of ships
     */
    public int getShipCount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many ships do you want? ");
        int shipCount = sc.nextInt();
        while(shipCount <= 0)
        {
            System.out.println("How many ships do you want? ");
            shipCount = sc.nextInt();
        }
        return shipCount;
    }

    /**
     * Helps determine the maximum size that input coordinates can be
     * @param myBoard the instance of Board class
     * @return the larger dimension (either the rows or columns)
     */
    public int getMaxSize(Board myBoard) {
        int maxSize = 0;
        if (myBoard.getRow() > myBoard.getColumn()) {
            maxSize = myBoard.getRow();
        } else {
            maxSize = myBoard.getColumn();
        }
        return maxSize;
    }

    /**
     * Gets the coordinates of the ship(s) location
     * @param notifyCounter a counter that tracks how many ships need to be made
     * @return an array list of type position that contains the coordinates
     */
    public ArrayList<Position> getShipPosition(int notifyCounter) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your ship coordinates for ship: " + notifyCounter + " in the form row,column ie x1,y1,x2,y2 etc");
        String inputCoordinates = sc.next();
        String[] arr = inputCoordinates.split(",");
        int[] array = new int[arr.length];
        for (int t = 0; t < arr.length; t++) {
            array[t] = Integer.parseInt(arr[t]);
        }

        ArrayList<Position> positionList = new ArrayList<Position>(10);

        for (int k = 0, j = 0; k < array.length; k = k + 2, j++) {
            Position positionElement = new Position(array[k], array[k + 1]);
            positionList.add(j, positionElement);
        }

        return positionList;

    }

    /**
     * Checks that the entered coordinates of the ship fit the dimensions of the board
     * @param myBoard the instance of Board class
     * @param positionList the array list of type position that contains the coordinates
     * @return boolean to determine if coordinates fit
     */
    public boolean checkCoordinates(Board myBoard, ArrayList<Position> positionList) {
        boolean coordinatesFit = true;
        for (int g = 0; g < positionList.size(); g++) {
            if ( (positionList.get(g).row > getMaxSize(myBoard)) || (positionList.get(g).column > getMaxSize(myBoard))) {
                System.out.println("The value entered is larger than the dimensions of the board ");
                coordinatesFit = false;
            }
        }
        return coordinatesFit;
    }

    /**
     * Checks if the coordinates are horizontally forming a ship
     * @param positionList the array list of type position that contains the coordinates
     * @return boolean to determine if coordinates form horizontal ship
     */
    public boolean checkHorizontal(ArrayList<Position> positionList)
    {
        boolean checkH = false;
        int counter =0;
        for (int i=0; i<positionList.size()-1; i++)
        {
            if (positionList.get(i).row == positionList.get(i+1).row)
            {
                counter++;
            }
        }
        if (counter == (positionList.size()-1))
        {
            checkH=true;
        }
        return checkH;
    }

    /**
     * Checks if the coordinates are vertically forming a ship
     * @param positionList the array list of type position that contains coordinates
     * @return boolean to determine if coordinates form vertical ship
     */
    public boolean checkVertical(ArrayList<Position> positionList)
    {
        boolean checkV = false;
        int counter =0;
        for (int i=0; i<positionList.size()-1; i++)
        {
            if (positionList.get(i).column == positionList.get(i+1).column)
            {
                counter++;
            }
        }
        if (counter == (positionList.size()-1))
        {
            checkV=true;
        }
        return checkV;
    }

    /**
     * Checks if the coordinates are diagonally forming a ship
     * @param positionList the array list of type position that contains coordinates
     * @return boolean to determine if coordinates form diagonal ship
     */
    public boolean checkDiagonal(ArrayList<Position> positionList)
    {
        boolean checkD = false;
        double denominator = (positionList.get(1).row - positionList.get(0).row);
        if (denominator > 0) {
            double m = (positionList.get(1).column - positionList.get(0).column)/ denominator;
            if (m == 1 || m == -1) {
                for (int i = 0, j = 1; i < positionList.size() - 1; i++, j++) {
                    if (((positionList.get(j).column - positionList.get(i).column) / (positionList.get(j).row - positionList.get(i).row) == m)) {
                        checkD = true;
                    }
                }
            }
        }
        return checkD;
    }


}





