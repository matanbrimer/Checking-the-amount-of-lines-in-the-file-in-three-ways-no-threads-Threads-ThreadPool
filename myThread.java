package ex2;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class myThread extends Thread{

	private String fileName;
	private int counter;

	/**
	 * constructor
	 * @param fileName - name of file
	 */
	public myThread(String fileName) {
		
		super();
		this.fileName = fileName;
		this.counter =0;
	}

	/**
	 *  count number of all lines in all files that were create.
	 *  and insert to counter in the thread.
	 */
	public void run() {
		
		try {
			File file = new File(fileName);
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
	}
	public int getCuonter() {
		return this.counter;
	}
	
}
