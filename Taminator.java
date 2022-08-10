/*
Author: Amirreza Pazira 
UCID: 30133500 
Tutorial: 08

Version: 1-April-2021

Program: Text based version of Conway's biological simulation "The Game of Life" 

Class Specific Features:

A child of Critter class tracks the information about the Taminator critter.
Can see the information about the simulation based on the array passed to it as parameter to one of its constructor
Can check for adjacent stars to Taminator and kill them.
Generates a random suitable location so that the Taminator can teleport.

** Reasons for adding methods and attributes or modifying existing ones is in the program body as comments**

Methods added:                                                        Attributes added:       
 
Added new constructor: public Taminator(Critter [][] aTaminator)      Critter [][] current;    
checkTam()                                                            final int COLUMNS = 10;          
teleport()                                                            final int ROWS = 10;
                                                                      int columnT = 0;
                                                                      int rowT = 0;
*/



/* As needed students can write additional methods and add attributes */
import java.util.Random;
public class Taminator extends Critter
{
    //Class attribute by James Tam. Students do not make any changes to it but
    //additional attributes may be added.
    public static final char DEFAULT_APPEARANCE = 'T';
    private Critter [][] current;
    // Added 2 attributes for the max size of the simulation
    private final int COLUMNS = 10;
    private final int ROWS = 10;
    // 2 attributes for the new Taminator position after teleport
    private int columnT = 0;
    private int rowT = 0;
    //Start of modifiable code written by James Tam
    //Taminator(), Taminator(char)
    //Students can make any changes.
    public Taminator()
    {
	super(DEFAULT_APPEARANCE);
    }

    public Taminator(char newAppearance)
    {
	super(newAppearance);
    }
        //End of modifiable code written by James Tam

    //Students can add additional methods below as needed.

    // New constructor which takes an 2D array of Critters as parameter which then make a shallow copy of it so that the changes 
    // take effect also on the array that was passed as a parameter also for later rounds the parameter array changes take effect on current array
    public Taminator(Critter [][] aTaminator)
    {
        current = aTaminator;
    }
    // Reason: To check the Taminator in the position passed as a parameter if it's needs to kill any stars.
    // Features: It will first check to see if the Taminator is at edge or not, then it will check the adjacent stars row by row and 
    // if there are any stars nearby the appearance of that Critter will be set to EMPTY by calling the mutator method in Critter class and the star dies.
    // This change will be shown in the current array and the one that was passed to the constructor.
    // Then it will pass the Taminator position to teleport method so that it can teleport to a random location
    public void checkTam(int row, int column){
        int minRow = 0;
        int minColumn = 0;
        int maxRow = 9;
        int maxColumn = 9;
        int m = 0;
        int n = 0;
        if (row - 1 >= 0)
            minRow = row - 1;
        if (column - 1 >= 0)
            minColumn = column - 1;
        if (row + 1 < ROWS)
            maxRow = row + 1;
        if (column + 1 < COLUMNS)
            maxColumn = column + 1;
        for (m = minRow; m <= maxRow; m++){
            for (n = minColumn; n <= maxColumn; n++){
                    if ((current[m][n] != current[row][column]) && (current[m][n].getAppearance() == Critter.DEFAULT_APPEARANCE))
                        current[m][n].setAppearance(EMPTY);
            }
        }
        teleport(row,column);
    }
    // Reason: To generate 2 numbers for row and column and change the position of Taminator.
    // Features: First it will generate two numbers for new row and column then it will check if the new location is empty or same as the current position of Taminator
    // if it's not empty or the same location it will generate new location until it find a suitable location
    // Then it will set the last Taminator location appearance to EMPTY
    // and the new location will be set to Taminator.DEFAULT_APPEARANCE = "T"
    public void teleport(int row,int column){
        Random num = new Random();

        rowT = num.nextInt(ROWS);
        columnT = num.nextInt(COLUMNS);
        while ((current[row][column] == current[rowT][columnT]) || (current[rowT][columnT].getAppearance() != EMPTY)){
            rowT = num.nextInt(ROWS);
            columnT = num.nextInt(COLUMNS);
        }
        current[row][column].setAppearance(EMPTY);
        current[rowT][columnT].setAppearance(Taminator.DEFAULT_APPEARANCE);
    }
}







