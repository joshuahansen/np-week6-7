import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.zip.*;

/**
*Task 3
*2 threads run in a loop of input-then-print
*Recieve input from console
*Pass input to thread
*Print out result
*Exit on single X character
**/

class Task3
{
    public static void main(String args[])
    {
        try {
            System.out.println("Enter input:");
            BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
        
            String userInput;
            userInput = keyboardInput.readLine();
            
            //create thread from CustomRunnable class
            Thread inputThread = new InputThread();
            Thread printerThread = new PrinterThread();
         
            while(!userInput.equals("X"))
            {
                //start thread
                inputThread.start();
                inputThread.setInput(userInput);
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
                userInput = keyboardInput.readLine();
            }
            //close keyboard input
            keyboardInput.close();
        }catch(IOException ex)
        {
            System.out.println("Error with Input/Output: " + ex);
        }
    }
}

/**
*Custom runnable class
*prints out user input set in constructor
**/
class InputThread extends Thread
{
    private String input;
    //override runnable run fuction to print out user input
    @Override
    public void run()
    {
        Global.string = this.input;
    }
    public void setInput(String input)
    {
        this.input = input;
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
