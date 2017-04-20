package UserPackage;

import java.io.*;

import java.util.*;

public class testing {
	public static void main(String args[]) throws IOException {
		File myFoo = new File(".log");
		FileOutputStream fooStream = new FileOutputStream(myFoo, false); // true to append
		                                                                 // false to overwrite.
		byte[] myBytes = "New Contents\n".getBytes();
		fooStream.write(myBytes);
		fooStream.close();
}
}
