package ex2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Ex2_1 {

	/**
	 *
	 * @param n - representing the number of text files
	 * the seed and bound together is to get the random number of lines in the file
	 * @param seed - the initial seed to create new random number
	 * @param bound - the upper bound  to create new random number
	 * @return array of the file names
	 */
	public static String[] createTextFiles(int n, int seed, int bound){
		
		String[] fileNames = new String[n];
		Random rand = new Random(seed);
		
		for (int i=0 ; i<n ; i++) {
			int numlines = rand.nextInt(bound);
			
			fileNames[i] = "file_"+(i+1) +".txt";
			
		try {
			FileWriter  myFile = new FileWriter(fileNames[i]);
			
			for (int k = 0 ;k<numlines ; k++) {
				myFile.write("hellow world");
			}
			myFile.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		}
		return fileNames;
	}

	/**
	 *
	 * @param fileNames -array of the file names
	 * @return The total number of lines of the files.
	 */
	public static int getNumOfLines(String[] fileNames){
		int sum = 0;
		
		for (int i=0 ; i< fileNames.length ; i++) {
			int counter =0;
			
			try {
				File file = new File(fileNames[i]);
				Scanner my = new Scanner(file);
				
				while (my.hasNextLine()) {
					my.nextLine();
					counter ++;
				}
				my.close();
				
			}
			catch( FileNotFoundException | NullPointerException | NoSuchElementException | IllegalStateException e) {
				e.printStackTrace();
			}
			sum+=counter;
		}
		return sum;
	}

	/**
	 * method using thread
	 * @param fileNames -array of the file names
	 * @return The total number of lines of the files.
	 */
	public static int getNumOfLinesThreads(String[] fileNames){
		
		myThread [] numThreads =new myThread[fileNames.length];
		int sum =0; 
		
		for (int i=0 ; i<fileNames.length; i++) {
			numThreads[i] = new myThread(fileNames[i]);
			numThreads[i].start();
		}
		
		
			for (myThread m : numThreads) {
				try
				{
					m.join();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
		
	}
			for (myThread m : numThreads) {
				sum += m.getCuonter();
			}
			return sum;
	}
	/**
	 * method using threadPool
	 * @param fileNames -array of the file names
	 * @return The total number of lines of the files.
	 */
	public static int getNumOfLinesThreadPool(String[] fileNames){

		ExecutorService excecutor = Executors.newFixedThreadPool(fileNames.length);

		int result =0;

		ArrayList<Future<Integer>> futures = new ArrayList<Future<Integer>> ();

		for (int i=0 ; i<fileNames.length ; i++) {

			futures.add( excecutor.submit(new myThreadCallable(fileNames[i])));
		}
		try {
			      // Block and wait for the result of the computation
			for (Future<Integer> future : futures) {
				result += future.get();
			}
			}
			catch (InterruptedException | ExecutionException e) {
			      e.printStackTrace();
			    }
		excecutor.shutdown();
		return(result);
		}
		
	public static void main(String[] args) {
		String [] fileNames = createTextFiles(300 , 50 ,1000);
		
//		Ex2_1 x = new Ex2_1();
		
		
		Long startTime = System.currentTimeMillis();
		System.out.println("TotalLines symple: "+ getNumOfLines(fileNames));
		Long endTime = System.currentTimeMillis();
		System.out.println("the time of the regular function: "+ (endTime- startTime));
		
		Long startTime1 = System.currentTimeMillis();
		System.out.println("TotalLines symple: "+ getNumOfLinesThreads(fileNames));
		Long endTime1 = System.currentTimeMillis();
		System.out.println("the time of the thread function: "+ (endTime1- startTime1));
		
		Long startTime2 = System.currentTimeMillis();
		System.out.println("TotalLines symple: "+ getNumOfLinesThreadPool(fileNames));
		Long endTime2 = System.currentTimeMillis();
		System.out.println("the time of the threadpool function: "+ (endTime2- startTime2));
		
		
	}
		
		
	}
	
