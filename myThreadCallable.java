package ex2;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class myThreadCallable implements Callable<Integer>{
	private String fileName;
	private int numlines =0 ;

	/**
	 * constructor
	 * @param fileName - name of file
	 */
	public myThreadCallable(String fileName) {
		this.fileName = fileName;
		this.numlines =0;
	}

	/**
	 *  count number of all lines in all files that were create.
	 *   and insert to the 'numlines' in the thread.
	 * @return 'numlines'.
	 * @throws Exception
	 */
	@Override
	public Integer call() throws Exception {

		try {
			File file = new File(fileName);
			Scanner my = new Scanner(file);
			
			while (my.hasNextLine()) {
				my.nextLine();
				numlines++;
			}
			my.close();
			
		}
		catch( FileNotFoundException | NullPointerException | NoSuchElementException | IllegalStateException e) {
			e.printStackTrace();
		}
		return this.numlines;
	}

}
