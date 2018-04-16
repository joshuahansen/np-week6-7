import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.zip.*;

/**
*Task 3
* Continue to get input until user enters "X"
* Print after each line
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
            //loop while user does not enter X
            while(!userInput.equals("X"))
            {
                Global.string = userInput;
                interrupt();
                //if printer has finished printing get next input
                if(Global.printerThread.isInterrupted())
                {
                    interrupted();
                    System.out.println("Enter input:");
                    userInput = keyboardInput.readLine();
                }
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
        * run while input Thread is still active
        */
        while(Global.inputThread.isAlive())
        {
            //print results after input thread has finished recieving input
            if(Global.inputThread.isInterrupted())
            {
                System.out.println("Printer Thread: " + Global.string);
                interrupt();
                //sleep thread to allow any overlap to clear
                try {
                    Thread.sleep(500);
                }catch(InterruptedException e)
                {
                }
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
