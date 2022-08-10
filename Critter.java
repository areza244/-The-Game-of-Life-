/*
Author: Amirreza Pazira 
UCID: 30133500 
Tutorial: 08

Version: 1-April-2021

Program: Text based version of Conway's biological simulation "The Game of Life" 


Class Specific Features:

Tracks information associated with the basic life form inhabiting the simulated world.
Can change the Critters appearance
Parent of Taminator class

*/

/* As needed students can write additional methods and add attributes */

public class Critter
{
    //Class attributes by James Tam. Students do not make any changes to these 3 but
    //additional attributes may be added.
    public static final char DEFAULT_APPEARANCE = '*';
    public static final char EMPTY = ' ';
    private char appearance;  


    //Start of modifiable code written by James Tam
    //Critter(), Critter(char), getAppearance(), setAppearance(char).
    //Students can make any changes.
    public Critter ()
    {
	setAppearance(DEFAULT_APPEARANCE);
    }
    public Critter(char ch)
    {
	setAppearance(ch);
    }

    public char getAppearance()
    {
	return appearance;
    } 

    public void setAppearance(char newAppearance)
    {
        appearance = newAppearance;
    } 
    //End of modifiable code written by James Tam

    //Students can add additional methods below as needed.

    //Code written by James Tam. Students: do not modify
    public String toString()
    {
	String s = "" + appearance;
        return(s);
    }
}

