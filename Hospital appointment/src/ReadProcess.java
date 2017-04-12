import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadProcess {

	public static String[] readFile() {
		// The path of the file to open.
		String fileName = "C:\\Users\\Donut\\Documents\\GitHub\\test1.txt";
		String temp = "";
		String finalString = "";
		int setEnd = 0;
		byte[] space = new byte[0];
		try {
			// the buffer is used to get the data.
			byte[] buffer = new byte[1000];
			FileInputStream inputStream = new FileInputStream(fileName);
			// The read method fills buffer with data.
			// the read method return -1 when it reaches the end of the data
			// stream
			while (inputStream.read(buffer) != -1) {
				// Convert the value in buffer to String and display it.
				temp = new String(buffer);

			}
			// Remove all the empty space from the consequence of using byte
			// reader
			for (int i = 0; i < temp.length(); i++) {
				if (temp.charAt(i) != 0) {
					finalString += temp.charAt(i);
				}
			}
			// close the input stream.
			inputStream.close();

		}
		// the anticipated errors
		catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
		String delim = "\\r?\\n";
		String testString = "";
		String[] token = finalString.split(delim);

		for (int i = 0; i < token.length; i++) {
			testString += token[i] + ",";
		}

		String[] finalSplit = testString.split(",");
	
		
		return finalSplit;
	}

}
