package com.mycompany.battleship;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Created by tuhinadasgupta on 7/8/18.
 */
public class Player {
    public void play(Board myBoard) {
        int counter = 0;
        // game runs until player has missed too many times or every ship coordinate has been hit
        while (counter < myBoard.maxMisses && !myBoard.shipSunk()) {
            String persona = "Player";
            printWelcomeMessage(myBoard);
            System.out.println("You have missed this many times: " + counter);
            myBoard.printDisplay(persona);
            if (counter==myBoard.maxMisses)
            {
                System.out.println("The game is over!");
                System.exit(0);
            }
            ArrayList<Position> positionList1 = getAttempt();
            if (checkAttemptCoordinates(positionList1, myBoard))
            {
                boolean returnValue = myBoard.setAttempt(positionList1);
                if (!returnValue) {
                    counter = counter;
                } else {
                    counter++;
                }
            }
            else if (!checkAttemptCoordinates(positionList1, myBoard))
            {
                System.out.println("Your strike coordinates were outside of the board dimensions");
            }
        }
        if (myBoard.shipSunk())
        {
            System.out.println("Congrats you've sunk all the ships!");
            System.out.println("FYI there were " + myBoard.shipCount);
        }
        else
        {
            String persona = "Player";
            System.out.println("Sorry you've used up all your misses; Game Over!");
            myBoard.printDisplay(persona);

        }
    }

    /**
     * Welcomes player to the game and tracks number of misses before game ends
     * @param myBoard instance of Board class
     */
    public void printWelcomeMessage(Board myBoard) {
        System.out.println("Welcome to Battleship! (X means you got a hit and O is a miss)");
        System.out.println("You have this many misses before your game ends: " + myBoard.maxMisses);
        System.out.println("Try to sink the ship(s)! ");
    }

    /**
     * Gets the player's strike coordinates
     * @return the player's strike coordinates in an array list of type position
     */
    public ArrayList<Position> getAttempt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the coordinates you would like to strike in the format row,col: ");
        String input = sc.next();
        if (input.equals("quit")) {
            System.out.println("You have quit this game");
            System.exit(0);

        }
        String[] coordinates = input.split(",");
        int[] array = new int[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            array[i] = Integer.parseInt(coordinates[i]);
        }

        ArrayList<Position> positionList1 = new ArrayList<Position>(10);

        for (int i = 0, j = 0; i < array.length; i = i + 2, j++) {
            Position positionElement = new Position(array[i], array[i + 1]);
            positionList1.add(j, positionElement);

        }
        return positionList1;

    }

    /**
     * Checks that the player's strike coordinates fit the dimensions of the board
     * @param positionList1 the array list of type position containing the strike coordinates
     * @param myBoard the instance of Board class
     * @return boolean check if the coordinates entered fit the board dimensions
     */
    public boolean checkAttemptCoordinates(ArrayList<Position> positionList1, Board myBoard)
    {
        boolean checkAttemptCoordinates = false;
        for (int g = 0; g < positionList1.size(); g++) {
            if (((positionList1.get(g).row > 0)&&(positionList1.get(g).row <= myBoard.row))&&((positionList1.get(g).column > 0)&&positionList1.get(g).column <= myBoard.column) )
            {
                checkAttemptCoordinates= true;
            }

        }
        return checkAttemptCoordinates;
    }
}













