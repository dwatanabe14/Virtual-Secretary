import java.io.*;
import java.util.*;

/**
 * Class deals with reading and writing data to a text file.
 * @author David Watanabe
 *
 */
public class TextScanner {
	
	private File file = new File("data.txt");
	
	/**
	 * sets data by reading a text file and creating an ArrayList
	 * @param in  the file to be read
	 * @return  ArrayList of data
	 */
	private ArrayList<String> setData(File in) {
		ArrayList<String> data = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				data.add(line);
			}
			reader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * gets data from the setter method above
	 * @return ArrayList of data
	 */
	public ArrayList<String> getData() {
			ArrayList<String> data = setData(file);
			return data;
	}
	
	/**
	 * writes data to a text file
	 * @param input  String to be written
	 */
	public void saveData(String input) {
		try{
			FileWriter fw = new FileWriter(file, false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(input);
			bw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * creates a new save file if one does not exist
	 */
	public void newFile() {
		try{
			if(!file.exists()) {
				file.createNewFile();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
