import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.zip.*;

/**
*Task 3
*Continue to get input until user enters "X"
* print after each line
**/

class Task3
{
    public static void main(String args[])
    {
            
        //start thread
        Global.inputThread.start();  
        Global.printerThread.start();
    }
}

/**
*Custom thread class
*sets global string for both threads to access
**/
class InputThread extends Thread
{
    //override runnable run fuction to print out user input
    @Override
    public void run()
    {
        try {
            System.out.println("Enter input:");
            BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
        
            String userInput;
            userInput = keyboardInput.readLine();
            while(!userInput.equals("X"))
            {
                Global.string = userInput;
            
                this.interrupt();
                userInput = keyboardInput.readLine();
            }   
            keyboardInput.close();
        }catch(IOException ex)
        {
            System.out.println("Error with Input/Output: " + ex);
        }
    }
}

class PrinterThread extends Thread
{
    //print out global input string
    @Override
    public void run()
    {
        /*
        * have printer wait for inputThread to be intrupted
        * before running.
        */
        while(true)
        {
            if(Global.inputThread.isInterrupted())
            {
                System.out.println("Thread Input: " + Global.string);
                this.interrupt();
            }
            if(!Global.inputThread.isAlive())
            {
                break;
            }
        }
    }
}
/**
* Global Class
* stores variables that both threads can access
*/
class Global
{
    public static String string;
    public static Thread inputThread = new InputThread();
    public static Thread printerThread = new PrinterThread();
} 
