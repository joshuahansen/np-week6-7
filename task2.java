import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.zip.*;

/**
*Task 2
*Data sharing between 2 threads
*Recieve input from console
*Pass input to thread
*Print out result
**/

class Task2
{
    public static void main(String args[])
    {
        //create thread from CustomRunnable class
        Thread inputThread = new InputThread();
        Thread printerThread = new PrinterThread();
            
        //start thread
        inputThread.start();
            
        /*
        * have printer wait for inputThread to be completed
        * before running.
        */
        try {
            inputThread.join();
        }catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        printerThread.start();
    }
}

/**
*Custom runnable class
*prints out user input set in constructor
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
            Global.string = userInput;
            
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
        System.out.println("Thread Input: " + Global.string);
    }
}
/**
* Global Class
* stores variables that both threads can access
*/
class Global
{
    public static String string;
} 
