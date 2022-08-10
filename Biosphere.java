/*
Author: Amirreza Pazira 
UCID: 30133500 
Tutorial: 08

Version: 1-April-2021

Program: Text based version of Conway's biological simulation "The Game of Life" 


Class Specific Features:

Creates the Simulation World by creating a 2D array of array of references to Critters based on the 2D array passed to it's constructor based on the input file.
Creating 4 array of references to Critters based on the input file for each state of the world in each round.
Responsible for tracking the current state of the simulated world.
Controls the Simulations and asks user if He/She wants to continue the simulation or end it.
Goes through the array of Critter to check each Critter and position and passing them to the corresponding method.
Then after every round it will update all the arrays based on the last state of the last round.
This class displys the Simulation.
This class controls the runtime of the program.

** Reasons for adding methods and attributes or modifying existing ones is in the program body as comments**

Additional Methods added:    Additional Attributes added:       Modified Methods:

check()                      Critter [][] newGen;               Biosphere(Critter [][] aWorld)
checkTam()                   Critter [][] tamGen;               Display()
checkDeath()                 Critter [][] prosperous;            runturn()
checkBorn()                  Taminator aTaminator;
updateTam()
updateCurrent()
updateNewGen()
updateProsperous()

/* As needed students can write additional methods and add attributes */
import java.util.Scanner;
public class Biosphere
{
    //Constant declarations and attributes by James Tam, don't change.
    public static final int ROWS = 10;
    public static final int COLUMNS = 10;
    public static final String HORIZONTAL_LINE = "  - - - - - - - - - -";
    public static final String HORIZONTAL_COUNT = "  0 1 2 3 4 5 6 7 8 9 ";
    private Critter [][] current;
    // Added 2 2D array of references to Critter for each state of the world and 1 which is essentially like current array but for prosperous rule
    public Critter [][] newGen;
    public Critter [][] tamGen;
    public Critter [][] prosperous;
    // Declaring a reference to Taminator class 
    private Taminator aTaminator;

    //Original code written by James Tam, don't modify
    public Biosphere(Critter [][] aWorld)
    {
        //Original code
        current = aWorld;

        //Student code
        // Added Code: The reason was to make a deep copy from the "current" 2D array of Critter which is a copy of the 2D array from the input file
        // each states 2D array will be a deep copy of the "current" array at the start later on they will change accordingly. 
        int r = 0;
        int c = 0;
        newGen = new Critter[ROWS][COLUMNS];
        for (r = 0; r < ROWS; r++)
            for (c = 0; c < COLUMNS; c++){
                newGen[r][c] = new Critter(current[r][c].getAppearance());
            }
        tamGen = new Critter[ROWS][COLUMNS];
        for (r = 0; r < ROWS; r++)
            for (c = 0; c < COLUMNS; c++){
                tamGen[r][c] = new Critter(current[r][c].getAppearance());
            }
        prosperous = new Critter[ROWS][COLUMNS];
        for (r = 0; r < ROWS; r++)
            for (c = 0; c < COLUMNS; c++){
                prosperous[r][c] = new Critter(current[r][c].getAppearance());
            }
        // Creating a Taminator object and passing the tamGen array to it which the last state of the round 2D array
        aTaminator = new Taminator(tamGen);
    }
    // Reason: to check each Critter in the Simulation
    // Features: Goes through every element of the "current" array then based on the appearances of the Critters
    // then it will pass their position as parameter to different methods to check for births and deaths.
    // Limitations: Can't change anything about the critters.
    public void check(){
        int r = 0;
        int c = 0;
        for (r = 0; r < ROWS; r++){
            for (c = 0; c < COLUMNS; c++){
                if (current[r][c].getAppearance() == Critter.DEFAULT_APPEARANCE)
                    checkDeath(r,c);
                if (current[r][c].getAppearance() == Critter.EMPTY){
                    checkBorn(r,c);
                }
            }
        }
    }
    // Reason: To check for the Taminator Critter in the Simulation
    // Features: Goes through every element of the "current" array then if there's a Taminator in the world it will
    // pass it's position to a method in Taminator class to check for adjacent starts to destroy and teleporting afterwards. 
    // Limitations: Can't change anything about the Taminator.
    public void checkTam(){
        int r = 0;
        int c = 0;
        for (r = 0; r < ROWS; r++){
            for (c = 0; c < COLUMNS; c++){
                    if (current[r][c].getAppearance() == Taminator.DEFAULT_APPEARANCE){
                        aTaminator.checkTam(r,c);
                    }
            }
        }
    }
    // Reason: To check the Critter in the position passed as a parameter if it's needs to die based on adjacent stars.
    // Features: It will first check to see if the star is at edge or not, then it will check the adjacent stars row by row and 
    // if there are less than 2 or more than 3 stars nearby the appearance of that Critter will be set to EMPTY by calling the mutator method in Critter class and the star dies.
    // This change will be shown in the newGen array and not the "current" because newGen is the second state's array.
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
                    if ((current[m][n] != current[row][column]) && (current[m][n].getAppearance() == Critter.DEFAULT_APPEARANCE))
                        death++;
            }
        }
        if ((death < 2) || (death > 3))
            newGen[row][column].setAppearance(Critter.EMPTY);
    }
    // Reason: To check the empty appearance Critter position passed as a parameter if there should be a new star born there based on adjacent stars.
    // Features: It will first check to see if the position is at edge or not, then it will check the adjacent stars row by row and 
    // if there are exactly 3 stars nearby the appearance of that Critter will be set to DEFAULT_APPEARANCE = "*" by calling the mutator method in Critter class and a new start is born.
    // This change will be shown in the newGen array and not the "current" because newGen is the second state's array.
    // Limitations: Can't kill stars only makes new stars if the requirements are met.
    public void checkBorn(int row, int column){
        int minRow = 0;
        int minColumn = 0;
        int maxRow = 9;
        int maxColumn = 9;
        int m = 0;
        int n = 0;
        int born = 0;
        if (row - 1 >= 0){
            minRow = row - 1;
        }
        if (column - 1 >= 0){
            minColumn = column - 1;
        }
        if (row + 1 < ROWS){
            maxRow = row + 1;
        }
        if (column + 1 < COLUMNS){
            maxColumn = column + 1;
        }
        for (m = minRow; m <= maxRow; m++){
            for (n = minColumn; n <= maxColumn; n++){
                    if ((current[m][n] != current[row][column]) && (current[m][n].getAppearance() == Critter.DEFAULT_APPEARANCE))
                        born++;
            }
        }
        if (born == 3)
            newGen[row][column].setAppearance(Critter.DEFAULT_APPEARANCE);
    }
    // Reason: After the second state which is Births and Deaths and changes to newGen array this method is called 
    // and it will update the last state array so that the Taminator class has access to the latest changes in the simulation.
    // Features: It goes through every element of the tamGen array and changes the Critter appearance to the corresponding Critter appearance
    // in the newGen array.
    public void updateTam(){
        int r = 0;
        int c = 0;
        for (r = 0; r < ROWS; r++)
            for (c = 0; c < COLUMNS; c++)
                    tamGen[r][c].setAppearance(newGen[r][c].getAppearance());
    }
    // Reason: After the third state which is Taminator and changes to tamGen array which is the last change in that round this method is called 
    // and it will update the "current" array so that it has the latest information for the next rounds previous generation state.
    // Features: It goes through every element of the current array and changes the Critter appearance to the corresponding Critter appearance
    // in the tamGen array.
    public void updateCurrent(){
        int r = 0;
        int c = 0;
        for (r = 0; r < ROWS; r++)
            for (c = 0; c < COLUMNS; c++)
                    current[r][c].setAppearance(tamGen[r][c].getAppearance());
    }
    // Reason: After the third state which is Taminator and changes to tamGen array which is the last change in that round this method is called 
    // and it will update the newGen array so that it has the latest information for the next rounds Births and Deaths state.
    // Features: It goes through every element of the newGen array and changes the Critter appearance to the corresponding Critter appearance
    // in the tamGen array.
    public void updateNewGen(){
        int r = 0;
        int c = 0;
        for (r = 0; r < ROWS; r++)
            for (c = 0; c < COLUMNS; c++)
                    newGen[r][c].setAppearance(tamGen[r][c].getAppearance());
    }
    // Reason: After the third state which is Taminator and changes to tamGen array which is the last change in that round this method is called 
    // and it will update the prosperous array so that the prosperousBiosphere has the latest information about the simulation.
    // Features: It goes through every element of the prosperous array and changes the Critter appearance to the corresponding Critter appearance
    // in the tamGen array.
    public void updateProsperous(){
        int r = 0;
        int c = 0;
        for (r = 0; r < ROWS; r++)
            for (c = 0; c < COLUMNS; c++)
                    prosperous[r][c].setAppearance(tamGen[r][c].getAppearance());
    }
    //The code used by the student was based on the display code written by James Tam
    // Modifications and Features: Added lines of code so that every state of the world is shown in row with labels and numbers.
    // and each state is displayed by going through that state's array and all the Critters and positions are bound by 4 lines around it.
    public void display()
    { 
        //Original code below: student can freely modify to fulfill assignment requirements
        //(add, delete, change existing code).
        int i;
        int r;
        int c;
        System.out.print("  PREVIOUS GENERATION");
        System.out.print("     ");
        System.out.print("    BIRTHS AND DEATHS");
        System.out.print("     ");
        System.out.println("         TAMINATOR");
	    System.out.print(HORIZONTAL_COUNT);
        System.out.print("     ");
        System.out.print(HORIZONTAL_COUNT);
        System.out.print("     ");
        System.out.println(HORIZONTAL_COUNT);
        System.out.print(" ");
        for (i = 0; i < ROWS; i++)
            System.out.print(" -"); //Line of dashes before the array
        System.out.print("       ");
        for (i = 0; i < ROWS; i++)
            System.out.print(" -"); //Line of dashes before the array
        System.out.print("       ");
        for (i = 0; i < ROWS; i++)
            System.out.print(" -"); //Line of dashes before the array
        System.out.println();
	    for (r = 0; r < ROWS; r++)
        {
	       System.out.print(r); //Line # before each row
	       for (c = 0; c < COLUMNS; c++)
	        {
		      System.out.print("|" + current[r][c]); //Bounding line left of array element
            }
            System.out.print("|"); //Bounding line at the of the row for the last element
            System.out.print("     "+r+"|"); //Bounding line at the of the row for the last element
            for (c = 0; c < COLUMNS; c++)
            {
                System.out.print(newGen[r][c] + "|"); //Bounding line left of array element
            }
            System.out.print("     "+r+"|"); //Bounding line at the of the row for the last element
            for (c = 0; c < COLUMNS; c++)
            {
                System.out.print(tamGen[r][c] + "|"); //Bounding line left of array element
            }
            System.out.println();
            System.out.print(" ");
            for (i = 0; i < ROWS; i++)
                System.out.print(" -");  //Bounding line below each array element
            System.out.print("       ");
            for (i = 0; i < ROWS; i++)
                System.out.print(" -");  //Bounding line below each array element
            System.out.print("       ");
            for (i = 0; i < ROWS; i++)
                System.out.print(" -");  //Bounding line below each array element
            System.out.println();
        }
    }
    //Original code written by James Tam, don't modify
    public Critter [][] getCurrent() 
    {
        return(current);
    }
    //Original code written by James Tam
    // Modifications: Added a runtime variable for stopping the program and added what methods should be called in order for each round
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
