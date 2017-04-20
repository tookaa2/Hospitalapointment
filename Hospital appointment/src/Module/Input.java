package Module;

import java.util.*;

public class Input {
	protected Scanner scan;

	public Input() {
		scan = new Scanner(System.in);
	}

	public int getInt(String askMessage, String ErrorMessage) {
		boolean looper = true;
		int tempNum = -1;

		try {
			Display.print(askMessage);
			tempNum = scan.nextInt();
		}

		catch (java.util.InputMismatchException e) {
			Display.print(ErrorMessage);
			tempNum = getInt(askMessage, ErrorMessage);
		}

		return tempNum;
	}

	public String getString(String askMessage,String ErrorMessage){
		boolean looper = true;
		String tempNum = null;

		try {
			Display.print(askMessage);
			tempNum = scan.nextLine();
		}

		catch (java.util.InputMismatchException e) {
			Display.print(ErrorMessage);
			tempNum = getString(askMessage, ErrorMessage);
		}

		return tempNum;
	}
	
	public char getChar(String askMessage,String ErrorMessage){
		boolean looper = true;
		char tempNum = 'x';

		try {
			Display.print(askMessage);
			tempNum = scan.next().charAt(0);
		}

		catch (java.util.InputMismatchException e) {
			Display.print(ErrorMessage);
			tempNum = getChar(askMessage, ErrorMessage);
		}

		return tempNum;
	}
}
