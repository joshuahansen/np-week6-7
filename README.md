# np-week6-7
## Threads
### Aims:
To learn how to use simple threads
### Tasks:

**1.** 
###### Thread basics
Write a program that consists of a main program and a thread. The main program receives a
line of input text from the console, passes it on to the thread as a parameter, and then
terminates. The thread prints out the text received from the main program, and then it
terminates.
Implementation: The thread should implement the Runnable interface. Once the thread
has produced the required output, it should stop by exiting the run() method.

**2.** 
###### Data sharing between threads.
There are two threads in this exercise: the input thread and the printer thread; the main
program’s only function is to invoke the two threads. The input thread should take a line
from the console and communicate it to the printer thread. The printer thread will receive
two text messages: one from the input thread and one from the main program; it should
output both text messages, one after the other. To ensure correct operation, the printer thread
should start printing only after the input thread has received the full input line. The threads
terminate after handling a single line.

**3.** 
###### Thread cooperation
Modify your program in task 2, so that the two threads run in a loop of input-then-print,
until the user enters a single ‘X’ character as input. The threads should terminate normally
after detecting the ‘X’.
