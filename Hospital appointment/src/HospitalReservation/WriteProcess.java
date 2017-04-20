package HospitalReservation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteProcess {
	public static void writeTo(String input,String location,boolean firstline) {
		// The name of the file to create.
		String fileName = location;
				///"C:\\test1\\caseA.txt";
		try {
			// Prepare a string to write
			String bytes = input;
			// Encode the string to binary value
			byte[] buffer = bytes.getBytes();
			FileOutputStream outputStream = new FileOutputStream(fileName, true);
			// The write method is used to write bytes into a file.
			if(firstline){
				
			}
			else{
				outputStream.write(13);// Carriage Return
				outputStream.write(10);// Line Feed
			}
			outputStream.write(buffer);
			// Always close files.
			outputStream.close();
		} catch (IOException ex) {
			System.out.println("Error writing file '" + fileName + "'");
		}
	}
	public static void clearFile(String location)throws IOException {
		
				File myFoo = new File(location);
				FileOutputStream fooStream = new FileOutputStream(myFoo, false); // true to append
				                                                                 // false to overwrite.
				byte[] myBytes = "".getBytes();
				fooStream.write(myBytes);
				fooStream.close();
		
		}
		
	}

