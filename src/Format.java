import java.util.*;

public class Format {
	
	private String separator = System.getProperty("line.separator");
	
	public String combineList(ArrayList<String> dataList) {
		String output = new String();
		for(String str: dataList){
			output = output + str + separator;
		}
		return output;
	}
	
	public void addData(String title, String month, String day, String year) {
		TextScanner scanner = new TextScanner();
		ArrayList<String> dataList = scanner.getData();
		String newData = "[" + month + "/" + day + "/" + year + "] " + title + " 0";
		dataList.add(newData);
		scanner.saveData(combineList(dataList));
	}
	
	public String radioText(String data) {
		String output = data.substring(0, data.length() - 2);
		if(data.substring(data.length() - 1).equals("1")) {
			output = output + " - Completed";
		}
		return output;
	}
	
	public void markComplete(int index) {
		TextScanner scanner = new TextScanner();
		ArrayList<String> data = scanner.getData();
		data.set(index, data.get(index).substring(0, data.get(index).length() - 1) + "1");
		scanner.saveData(combineList(data));
	}
	
	public void markIncomplete(int index) {
		TextScanner scanner = new TextScanner();
		ArrayList<String> dataList = scanner.getData();
		dataList.set(index, dataList.get(index).substring(0, dataList.get(index).length() - 1) + "0");
		scanner.saveData(combineList(dataList));
	}
	
	public void remove(int index) {
		TextScanner scanner = new TextScanner();
		ArrayList<String> dataList = scanner.getData();
		dataList.remove(index);
		scanner.saveData(combineList(dataList));
	}
	
}
