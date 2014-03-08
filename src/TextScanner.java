import java.io.*;
import java.util.*;
			
public class TextScanner {
	
	private File file = new File("data.txt");
	
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
	
	public ArrayList<String> getData() {
			ArrayList<String> data = setData(file);
			return data;
	}
	
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
