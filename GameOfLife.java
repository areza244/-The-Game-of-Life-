/*
Author: Amirreza Pazira 
UCID: 30133500 
Tutorial: 08

Version: 1-April-2021

Program: Text based version of Conway's biological simulation "The Game of Life" 

Program Features: 
Creates a 10x10 turn based Simulation.
The starting data and positions will be read from a text file and the simulation will run on a turn by turn basis. 
The starting data consists of empty spaces or stars to be read into an array of references to 'Critters'. 
Each turn the rules of births and deaths will change the pattern.
There are two versions for the Birth and Death rules and user can choose which one to activate at the start.
Each turn the program goes through every block in the simulation by checking every element in array of Critters.
If the Critter appearance is empty that means that block is empty then it will be passed to another method to check if it's possible for a star to be born there based on adjacent stars..
If the Critter appearance is a start it means that block is full it will passed to another method to check if that star needs to die or not based on adjacent stars.
If the Critter appearance is a 'T' that block is filled by a Taminator then it will passed to another method to check for adjacent stars to get destroyed by Taminator and then it will teleport to a random location.
Each state of the world will be displayed and user can continue to infinite rounds.
User can stop the program.

Program Limitations:
Only Creates a 10x10 Simulation cannot be bigger.
User can't change any appearances.
User can't change the rules of Births and Deaths in the middle of the turns.
There's at most one Taminator.


Class Specific Features:

This is the Driver for the program. In the starting code it: reads the information from file, creates a biosphere object 
or ProsperousBiosphere object and starting the rounds based on the user's selection

Modified the class:

Added a while loop for asking user's selection for the births and deaths rule it will keep running until the user enters 
'P' or 'p' and 'r' or 'R' then it will create a biosphere object or ProsperousBiosphere object accordingly.

*/
import java.util.Scanner;

/* No additional methods and nor attributes to be added. */ 

public class GameOfLife
{
    public static void main (String [] args)
    {
        //Start of code written by James Tam, students can freely modify (but still need to 
        //fulfill assignment requirements and stylistic approaches).
        Biosphere regular;
        String input = "";
        boolean run = false;
        Scanner in = new Scanner(System.in);
        while (run == false){
        	System.out.println("Choose Births and Deaths Rules! ");
        	System.out.print("Please choose 'P'rosperousBioSphere or 'R'egularBioSphere: ");
        	input = in.nextLine();
        	switch(input){
        		case "P":
        		case "p":
					regular = new ProsperousBiosphere(FileInitialization.read());
					regular.runTurn();
					run = true;
					break;
				case "R":
				case "r":
					regular = new Biosphere(FileInitialization.read());
					regular.runTurn();
					run = true;
					break;
				default:
					System.out.println("Please enter P or R!");
			}
        }
    }
}



