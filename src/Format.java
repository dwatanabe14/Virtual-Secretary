import java.util.*;
import java.awt.*;
import java.awt.font.*;
import javax.swing.*;

/**
 * Class deals with formatting data so it can be used by other classes.
 * @author David Watanabe
 *
 */
public class Format {
	
	private final String separator = System.getProperty("line.separator"); // does the same thing as "\n", but ensures platform compatibility
	
	/**
	 * Combines an ArrayList of data into a single string that will be saved to a text file.
	 * @param dataList  ArrayList of data
	 * @return String of data
	 */
	public String combineList(ArrayList<String> dataList) {
		String output = new String();
		for(String str: dataList){
			output = output + str + separator;
		}
		return output;
	}
	
	/**
	 * adds new data to the list and saves it to a text file
	 * @param title  Title of the assignment
	 * @param month  month/day/year represents the due date
	 * @param day
	 * @param year
	 */
	public void addData(String title, String month, String day, String year) {
		TextScanner scanner = new TextScanner();
		ArrayList<String> dataList = scanner.getData();
		String newData = "[" + month + "/" + day + "/" + year + "] " + title + " 0"; // Note: the number at the end of the string indicates the completion status. 0 = incomplete (default). 1 = complete.
		dataList.add(newData);
		scanner.saveData(combineList(dataList)); //save the all data to text file
	}
	
	/**
	 * formats data into the text displayed by assignments()
	 * @param data  String of data to be manipulated
	 * @return the text to be displayed
	 */
	public String radioText(String data) {
		String output = data.substring(0, data.length() - 2);
		return output;
	}
	
	/**
	 * if the assignment is marked as completed, adds a strike-through attribute to the font.
	 * @param data  String of data to be manipulated
	 * @return font (returns default font if assignment is incomplete)
	 */
	public Font radioFont(String data) {
		int completion = Integer.parseInt(data.substring(data.length() - 1));
		Font font = UIManager.getDefaults().getFont("RadioButton.font");
		if(completion == 1) { // the code inside of this if statement comes from Stack Overflow
			Map<TextAttribute, Object>  attributes = new HashMap<TextAttribute, Object>();
			attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON); 
			font = font.deriveFont(attributes);
		}
		return font;
	}
	
	/**
	 * edits data to indicate that an assignment is complete.
	 * @param index  location in the data ArrayList
	 */
	public void markComplete(int index) {
		TextScanner scanner = new TextScanner();
		ArrayList<String> data = scanner.getData();
		data.set(index, data.get(index).substring(0, data.get(index).length() - 1) + "1"); // 1 = complete
		scanner.saveData(combineList(data));
	}
	
	/**
	 * edits data to indicate that an assignment is incomplete.
	 * @param index  location in the data ArrayList
	 */
	public void markIncomplete(int index) {
		TextScanner scanner = new TextScanner();
		ArrayList<String> dataList = scanner.getData();
		dataList.set(index, dataList.get(index).substring(0, dataList.get(index).length() - 1) + "0"); // 0 = incomplete
		scanner.saveData(combineList(dataList));
	}
	
	/**
	 * deletes data
	 * @param index  location in the data ArrayList
	 */
	public void remove(int index) {
		TextScanner scanner = new TextScanner();
		ArrayList<String> dataList = scanner.getData();
		dataList.remove(index);
		scanner.saveData(combineList(dataList));
	}
	
}
