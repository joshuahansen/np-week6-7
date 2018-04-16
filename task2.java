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
        Thread printerThread = new PrinterThread("Main Program");
            
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
*Custom thread class
*Sets global string from user input
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
/**
*Custom Thread class
*Prints global string
*/
class PrinterThread extends Thread
{
    private String mainInput;

    public PrinterThread(String input)
    {
        this.mainInput = input;
    }
    //print out global input string
    @Override
    public void run()
    {
        System.out.println("Thread Printer: " + mainInput);
        System.out.println("Thread Printer: " + Global.string);
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
