import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.zip.*;

/**
*Task 1 
*Recieve input from console
*Pass input to thread
*Print out result
**/
class Task1
{
    public static void main(String args[])
    {
        try {
            System.out.println("Enter input:");
            BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
        
            String userInput;
            userInput = keyboardInput.readLine();
            
            //create thread from CustomRunnable class
            Thread firstThread = new Thread(new CustomRunnable(userInput));
            
            //start thread
            firstThread.start();
            
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
class CustomRunnable implements Runnable
{
    private String input;
    //constructor for runnable. sets user input.
    public CustomRunnable(String input)
    {
        this.input = input;
    }
    //override runnable run fuction to print out user input
    @Override
    public void run()
    {
        System.out.println("Thread: " + this.input);
    }
}
