/*
Author: Amirreza Pazira 
UCID: 30133500 
Tutorial: 08

Version: 1-April-2021

Program: Text based version of Conway's biological simulation "The Game of Life" 


Class Specific Features:

Class for reading starting positions from the input file

*/
import java.io.FileReader;
import java.io.BufferedReader;

/*
  Author:  James Tam
  Version: February 16, 2021

*/

public class FileContainer
{
    private BufferedReader br;
    private FileReader fr; 

    public FileContainer(BufferedReader aBufferedReader, 
                         FileReader aFileReader)
    {
        br = aBufferedReader;
        fr = aFileReader;
    }

    public BufferedReader getBufferedReader()
    {
        return(br);
    }

    public FileReader getFileReader()
    {
        return(fr);
    }
}