/*
Author: Amirreza Pazira 
UCID: 30133500 
Tutorial: 08

Version: 1-April-2021

Program: Text based version of Conway's biological simulation "The Game of Life" 


Class Specific Features:

when the user choose prosperousbiosphere rule the 2D array of critter read from the input file will be passed to constructor
of this class but the constructor body will the super() to use the constructor of the parent class biosphere to create the 10x10
2D array simulation world.
Controls the Simulations when the prosperous rule is selected and asks user if He/She wants to continue the simulation or end it.
Goes through the array of Critter to check each Critter and position and passing them to the corresponding method.
Then after every round it will call the update method in biosphere class to update all the arrays based on the last state of the last round.
This class can controls the runtime of the program when prosperous rule is selected.

** Reasons for adding methods and attributes or modifying existing ones is in the program body as comments**

Methods added and overridden:           

check()                                                                      
checkDeath()                             
checkBorn()                  
runturn()


/* As needed students can write additional methods and add attributes */
import java.util.Scanner;
public class ProsperousBiosphere extends Biosphere // Students can't change this inheritance relation.
{
    //Students can make any changes except for the relationship.
    public ProsperousBiosphere(Critter [][] aWorld)
    {
        super(aWorld);
    }
    // Added and overriding parent class method: To check each Critter in the Simulation by going through prosperous array but in ProsperousBiosphere
    // Features: Goes through every element of the prosperous array then based on the appearances of the Critters
    // then it will pass their position as parameter to different methods to check for births and deaths.
    // Limitations: Can't change anything about the critters.
    public void check(){
        int i = 0;
        int m = 0;
        for (i = 0; i < ROWS; i++){
            for (m = 0; m < COLUMNS; m++){
                if (prosperous[i][m].getAppearance() == Critter.DEFAULT_APPEARANCE)
                    checkDeath(i,m);
                if (prosperous[i][m].getAppearance() == Critter.EMPTY)
                    checkBorn(i,m);
            }
        }
    }
    // Added and overriding parent class method: To check the Critter in the position passed as a parameter if it's needs to die based on adjacent stars.
    // but with ProsperousBiosphere rules.
    // Features: It will first check to see if the star is at edge or not, then it will check the adjacent stars row by row and 
    // if there are less than 1 or more than 4 stars nearby the appearance of that Critter will be set to EMPTY by calling the mutator method in Critter class and the star dies.
    // This change will be shown in the newGen array because it can access it from the parent class and not the prosperous because newGen is the second state array.
    // Limitations: Can't make new stars only kills them if the requirements are met.
    public void checkDeath(int row, int column){
        int minRow = 0;
        int minColumn = 0;
        int maxRow = 9;
        int maxColumn = 9;
        int m = 0;
        int n = 0;
        int death = 0;
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
                    if ((prosperous[m][n] != prosperous[row][column]) && (prosperous[m][n].getAppearance() == Critter.DEFAULT_APPEARANCE))
                        death++;
            }
        }
        if ((death < 1) || (death > 4))
            newGen[row][column].setAppearance(Critter.EMPTY);
    }
    // Added and overriding parent class method: To check the empty appearance Critter position passed as a parameter 
    // if there should be a new star born there based on adjacent stars but with ProsperousBiosphere rules.
    // Features: It will first check to see if the star is at edge or not, then it will check the adjacent stars row by row and 
    // if there are exactly 4 stars nearby the appearance of that will be set to DEFAULT_APPEARANCE = "*" by calling the mutator method in Critter class and a new start is born.
    // This change will be shown in the newGen array because it can access it from the parent class  and not the prosperous because newGen is the second state's array.
    // Limitations: Can't kill stars only makes new stars if the requirements are met.
    public void checkBorn(int row, int column){
        int minRow = 0;
        int minColumn = 0;
        int maxRow = 9;
        int maxColumn = 9;
        int m = 0;
        int n = 0;
        int born = 0;
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
                    if ((prosperous[m][n] != prosperous[row][column]) && (prosperous[m][n].getAppearance() == Critter.DEFAULT_APPEARANCE))
                        born++;
            }
        }
        if (born == 4)
            newGen[row][column].setAppearance(Critter.DEFAULT_APPEARANCE);
    }
    // Added and overriding parent class method: This method is like the runTurn method in Biosphere class but here it will call overridden methods in
    // Prosperous class first when the ProsperousBiosphere is chosen by the user.
    // Features: This method handles each round states order by calling methods in order then after displaying the round it will
    // call update methods to get ready for the next round then it will ask the user either to continue or enter 'Q' or 'q' to quit the program
    // the program runs until user quits.
    public void runTurn()
    {
        //Original code below: student can freely modify to fulfill assignment requirements
        //(add, delete, change existing code).
        	Scanner in = new Scanner(System.in);
        	boolean run = false;
        	check();
        	updateTam();
        	checkTam();
        	display();
        	updateCurrent();
        	updateNewGen();
        	updateProsperous();
        	String input;      
        	System.out.print("Press enter to continue or 'q' to quit: ");
        	input = in.nextLine();
        	System.out.println();
        	switch(input){
            	case "q":
            	case "Q":
                	run = true;
                	break;
            }
        while (run == false){
        	check();
        	updateTam();
        	checkTam();
        	display();
        	updateCurrent();
        	updateNewGen();
        	updateProsperous();
        	System.out.print("Press enter to continue or 'q' to quit: ");
        	input = in.nextLine();
        	System.out.println();
        		switch(input){
            	case "q":
            	case "Q":
                	run = true;
                	break;
            }
        }        
    }
}
