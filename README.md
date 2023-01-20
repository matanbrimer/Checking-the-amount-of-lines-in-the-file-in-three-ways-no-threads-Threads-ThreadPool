We created several text files and calculated the total number of lines in these files. We will use three methods:

• Normal method without the use of threads.
• use of threads.
• Use of threadpool.

1. createTextFiles - A function that accepts a natural number n and a number of rows. and creates n files with this number of lines in each file.

2. getNumOfLines - Receives an array with the names of the files, and returns the total number of lines in all of them together.

3. getNumOfLinesThreads - Receives an array with the names of the files, and returns the total number of lines in all of them together. 
   this function will use a class that inherits from the thred class (we called it mythread).

4. getNumOfLinesThreadPool - Receives an array with the names of the files, and returns the total number of lines in all of them together. 
   this function will use a class that implements interface callable , such that the method "call" calculate how much rows have one file.
   
   
   running times:
   
   getNumOfLines (no threads): takes the longest time.
   
   getNumOfLinesThreads: much better than "getNumOfLines", and very close to "getNumOfLinesThreadPool". but most of the time 
                         (Especially when the number of files increases) less good than "getNumOfLinesThreadPool".
                         
   getNumOfLinesThreadPool: the best running times in most cases.
   
   And there is an explanation to that:
   
   The reason that the best running times are with the use of threadpool is , because it contains an array of threads and enables the reuse of threads that have          finished a task.Instead of creating new threads.
   
   The reason that using a regular thread is faster than the function that calculates the tasks one after the other(regulary) is. because the essence of the thread is    to perform tasks at the same time. and so that the program is not waiting to the sum of rows of one file for starting to sum another , therefor it is taking less      time.
   
   the methods are in the project , you can run them there...
   


   
