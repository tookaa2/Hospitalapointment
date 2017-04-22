package HospitalReservation;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class HospitalProgramMenu {
	public static void mainMenu() {
		Database mainDatabase=new Database("C:\\Users\\Donut\\Documents\\GitHub\\test1.txt","C:\\Users\\Donut\\Documents\\GitHub\\appoint1.txt","C:\\Users\\Donut\\Documents\\GitHub\\change1.txt");
		
		
		
		
		
		
		System.out.println(dateToday + "," + month + "," + year);
		today.add(Calendar.DATE, 15);
		dateToday = today.get(Calendar.DATE);
		month = today.get(Calendar.MONTH);
		year = today.get(Calendar.YEAR) - 543;
		System.out.println(dateToday + "," + month + "," + year);
		Date date = new Date();
		
		
		Main(mainList, appointmentData1, changeData);
	}

	public static void Main(UserList mainList, AppointmentList appointData, AppointmentList changeData)
			throws IOException {
		User temporaryUser;
		int failTries = 1;
		Scanner input = new Scanner(System.in);

		System.out.println("CAMT Hospital reservation center. \nPlease login to continue \nUsername:");
		String username = input.next();
		while (!mainList.verifyExist(username)) {
			System.out.println("Incorrect Username,please try again.\nUsername:");
			username = input.next();
		}
		System.out.println("Password:");

		String password = input.next();
		while (!mainList.verifyPassword(username, password) && failTries < 3) {
			System.out.println("Incorrect Password,please try again.\nUsername:");
			password = input.next();
			failTries++;
		}
		if (mainList.verifyExist(username)) {
			if (mainList.verifyPassword(username, password)) {
				System.out.println("Login Success!");
				temporaryUser = mainList.getUserObject(username);
				if (temporaryUser instanceof Doctor) {
					menuDoctor(mainList, appointData, temporaryUser, changeData);
				} else if (temporaryUser instanceof Reception) {
					menuReception(mainList, appointData, temporaryUser, true);
				} else {
					menuPatient(mainList, appointData, temporaryUser, changeData, true);
				}

			} else {
				System.out.println("Invalid! Program terminating. . .");
			}
		}

	}
}
